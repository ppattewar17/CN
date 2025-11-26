<!DOCTYPE html>
<html>
<head>
    <title>PHP Array </title>
</head>
<body>
    <h2>Array</h2>
    <?php
        $person = array("name" => "Prathmesh", "age" => 20, "city" => "Nanded");

        echo "Associative Array Elements:<br>";
        foreach ($person as $key => $value) {
            echo $key . ": " . $value . "<br>";
}       
    ?>

</body>
</html>
