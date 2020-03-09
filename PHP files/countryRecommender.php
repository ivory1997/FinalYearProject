<?php
require "server_connection.php";
$user_email = $_POST["identifier_loginEmail"];
$username = $_POST["identifier_loginName"];
$result = mysqli_query($conn,$mysql_query);
$xml = file_get_contents("https://thawing-headland-15724.herokuapp.com/");
	
	echo "true,".$xml;
	
?>