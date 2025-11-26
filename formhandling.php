<!DOCTYPE html>
<html>
<body>
    <form method="post" action="">
        Name: <input type="text" name="name"><br>
        E-mail: <input type="text" name="email"><br>
        <input type="submit" value="Submit">
    </form>

    <?php
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $name = htmlspecialchars($_POST["name"]);
        $email = htmlspecialchars($_POST["email"]);
        echo "Welcome " . $name . "<br>";
        echo "Your email address is: " . $email . "<br>";
    }
    ?>
</body>
</html>
