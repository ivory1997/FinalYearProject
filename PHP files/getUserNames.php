<?php
require "server_connection.php";
$user_email = $_POST["identifier_loginEmail"];
$user_name = $_POST["identifier_loginName"];
$mysql_query = "SELECT * FROM users";
$result = mysqli_query($conn,$mysql_query);
$users = array();
if(mysqli_num_rows($result)>0){

		while($row = mysqli_fetch_assoc($result))
    		{   
    			$userTemp = $row["name"];
    			array_push($users, $userTemp);
    		}
	
	
	echo "true,";
	for ($x = 0; $x <= sizeof($users); $x++) {
	   
            echo $users[$x].",";
	  
	  
	   
	    
	    
}
}
else{
	echo "Retrieval of users was not successful... :(";
}
?>