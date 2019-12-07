<?php
require "server_connection.php";

$user_email = $_POST["identifier_deleteEmail"];


$query = "DELETE FROM users WHERE email = '$user_email'";
$query2 = "DELETE FROM countries WHERE email = '$user_email'";
if(mysqli_query($conn,$query)){
	echo "<h2>Data Successfully Deleted!</h2>";
}
else{
	echo "<h2>Data was unable to be deleted from database :(.</h2>";
}
if(mysqli_query($conn,$query2)){
	echo "<h2>Data Successfully deleted!</h2>";
}
else{
	echo "<h2>Data was unable to be deleted from database.</h2>";
}
?>