<?php
require "server_connection.php";

$user_email = $_POST["updateCountriesEmail"];
$countriesValue = $_POST["updateCountriesValue"];
$countriesNum= $_POST["updateCountriesNum"];
$countriesNumInt = (int)$countriesNum;

if($countriesNumInt == 1)
{
	$countriesNumInt2 = 2;
}
else if($countriesNumInt == 2)
{
	$countriesNumInt2 = 1;
}
	

$query = "UPDATE countries 
SET  $countriesValue = '$countriesNumInt2'
WHERE email = '$user_email'";

if(mysqli_query($conn,$query)){
	echo "<h2>Data Successfully Updated!</h2>";
}
else{
	echo "<h2>Data was unable to be Updated :(.</h2>";
}
?>