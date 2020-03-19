<?php
require "server_connection.php";
$user_email = $_POST["identifier_email"];

$mysql_query = "SELECT * FROM users WHERE email = '$user_email'";
$result = mysqli_query($conn,$mysql_query);

if(mysqli_num_rows($result)>0){
	$row = mysqli_fetch_assoc($result);
	$picture = $row["picture"];
	

	
	
	echo $picture;
}
else{
	echo "Retrieval of picture was not successful... :(";
}
?>