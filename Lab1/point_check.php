<?php
    $start_time = microtime(true);
    session_start();

    // Returns distance between points (x, y) and (0, 0).
    function distance0($x, $y) {
        return sqrt($x * $x + $y * $y);
    }

    function is_in_area($x, $y, $r) {
        $x = floatval($x);
        $y = floatval($y);
        $r = floatval($r);

        //  square
        if ($x >= 0 && $x <= $r/2 && $y >= 0 && $y <= $r )
            return true;

        // circle quarter
        if ($x <= 0 && $y >= 0 && distance0($x, $y) <= $r)
            return true;

        // triangle
        if ($x >= 0 && $y <= 0 && abs($x) + abs($y) <= $r)
            return true;

        return false;
    }

    function answer_to_string($answ_value) {
        if ($answ_value === true)
            return "true";
        if ($answ_value === false)
            return "false";
        return "how i know.";
    }

    function ensure_int($value, $min, $max) {
        if (filter_var($value, FILTER_VALIDATE_INT) === false)
            return false;
        $ivalue = intval($value);
        return $ivalue >= $min && $value <= $max;
    }

    function ensure_float($value, $min, $max) {
        if (filter_var($value, FILTER_VALIDATE_FLOAT) === false)
            return false;
        $fvalue = floatval($value);
        return $fvalue > $min && $fvalue < $max;
    }

    function time_now() {
        date_default_timezone_set('Europe/Moscow');
        // define("SECONDS_IN_HOUR", 3600);

        // Time php for some reason differs from the time on the server for an hour.
 
        // $time_now = time() - SECONDS_IN_HOUR;
        $time_now = time() ;

        return date("H:i:s", $time_now);
    }

    // Deletes entry with specified id by unsetting its values.
    // Should be used to save some memory.
    // Assumes id is valid integer.
    function delete_entry($id) {
        unset($_SESSION["x_value" . $id]);
        unset($_SESSION["y_value" . $id]);
        unset($_SESSION["r_value" . $id]);
        unset($_SESSION["result" . $id]);
        unset($_SESSION["time" . $id]);
    }

    // Saves info about one checked point.
    // Assumes that input is valid.
    function save_entry($x, $y, $r, $result) {
        if (!isset($_SESSION["saves_cnt"]))
            $cnt = 0;
        else
            $cnt = intval($_SESSION["saves_cnt"]);

        if (!isset($_SESSION["saves_start_from"]))
            $start_from = 0;
        else
            $start_from = intval($_SESSION["saves_start_from"]);

        define("MAX_ENTRIES", 50);

        $_SESSION["x_value" . $cnt] = strval($x);
        $_SESSION["y_value" . $cnt] = strval($y);
        $_SESSION["r_value" . $cnt] = strval($r);
        $_SESSION["result" . $cnt] = ($result ? "true" : "false");
        $_SESSION["time" . $cnt] = time_now();

        $cnt += 1;
        $_SESSION["saves_cnt"] = strval($cnt);

        while ($cnt - $start_from > MAX_ENTRIES) {
            delete_entry($start_from);
            $start_from += 1;
        }

        $_SESSION["saves_start_from"] = strval($start_from);
    }

    // returns all save entries as a 2D array
    function load_entries() {
        if (!isset($_SESSION["saves_cnt"]))
            return array();
        $cnt = intval($_SESSION["saves_cnt"]);

        if (!isset($_SESSION["saves_start_from"]))
            $start_from = 0;
        else
            $start_from = intval($_SESSION["saves_start_from"]);

        $arr = array();
        for ($i = $start_from; $i < $cnt; $i++) {
            array_unshift($arr, array(
                htmlspecialchars($_SESSION["x_value" . $i]),
                htmlspecialchars($_SESSION["y_value" . $i]),
                htmlspecialchars($_SESSION["r_value" . $i]),
                htmlspecialchars($_SESSION["result" . $i]),
                htmlspecialchars($_SESSION["time" . $i])
            ));
        }
        return $arr;
    }

    if ($_GET["clear"] === "1") {
        session_unset();
        header('Location: ' . $_SERVER['HTTP_REFERER'] . '&didClear=1');
        exit(0);
    }

    $x = htmlspecialchars($_GET["coord_x"]);
    $y = htmlspecialchars($_GET["coord_y"]);
    $r = htmlspecialchars($_GET["r_value"]);
    $valid = false;

    if (strlen($x) > 7)
        $x = substr($x, 0, 7);
    if (strlen($y) > 7)
        $y = substr($y, 0, 7);
    if (strlen($r) > 7)
        $r = substr($r, 0, 7);

    if (!ensure_int($x, -5, 3))
        $answer = "Incorrect x";
    else if (!ensure_float($y, -5.0, 5.0))
        $answer = "Incorrect y";
    else if (!ensure_float($r, 0.9, 3.1))
        $answer = "Incorrect r";
    else {
        $is_in_area = is_in_area($x, $y, $r);
        $answer = answer_to_string($is_in_area);
        $valid = true;
        if ($_GET['didClear'] !== '1')
            save_entry($x, $y, $r, $is_in_area);
    }
?>


<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8" />
    <title>Лабораторная работа 1</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/style-point-check.css" />
</head>

<body>
    <main>
        <header>
            <h1>
                Le Tuan Dung P3200 <br>
            Вариант 18011</h1>
        </header>

        <table class="bordered stretch">
            <tr class="result_row">
                <td>x</td>
                <td><?php echo $x; ?></td>
            </tr>
            <tr class="result_row">
                <td>y</td>
                <td><?php echo $y; ?></td>
            </tr>
            <tr class="result_row">
                <td>R</td>
                <td><?php echo $r; ?></td>
            </tr>
            <tr class="result_row">
                <td>Result</td>
                <td><?php echo $answer; ?></td>
            </tr>
        </table>
        <a href="index.html" id="back-link">another input</a>
        <br>

        <div id="results-holder-outer">
            <div id="img-holder">
                <canvas id="plot-canvas" width="400" height="400"></canvas>
            </div>

            <div id="last-results" class="bordered">
                <div id="last-results-inner">
                    <table id="results-log-table" class="stretch">
                        <tr>
                            <td>X</td>
                            <td>Y</td>
                            <td>R</td>
                            <td>Result</td>
                            <td>Time</td>
                        </tr>

                        <?php
                            $entries = load_entries();
                            foreach ($entries as $entry) {
                                echo "<tr>";
                                foreach ($entry as $value) {
                                    echo "<td>";
                                    echo $value;
                                    echo "</td>";
                                }
                                echo "</tr>";
                            }
                        ?>
                    </table>
                </div>

                <?php
                    $clear_url = $_SERVER['REQUEST_URI'] . '&clear=1';
                    echo '<a href="' . $clear_url . '">clear</a>';
                ?>
            </div>
        </div>

        <footer>
            <?php
                define("MS_IN_SECOND", 1000);

                echo "curent time ";
                echo time_now();
                echo "<br>";

                echo "execution time ";
                $exec_time_ms = (microtime(true) - $start_time) * MS_IN_SECOND;
                echo number_format($exec_time_ms, 3, '.', '') . " мс";
            ?>
        </footer>
    </main>

    <script type="text/javascript" src="js/browser-support.js"></script>
    <script type="text/javascript" src="js/point-check.js"></script>
    <script type="text/javascript">
        <?php
            if ($valid)
                echo "drawPlot.drawPlotValid(" .
                     $x . "," . $y . "," . $r . ");";
            else
                echo "drawPlot.drawPlotInvalid();";
        ?>
    </script>
</body>
</html>
