<?php
require "server_connection.php";
$user_email = $_POST["identifier_loginEmail"];
$user_name = $_POST["identifier_loginName"];
$mysql_query = "SELECT * FROM Friends WHERE user_name = '$user_name'";
$result = mysqli_query($conn,$mysql_query);
$friends = array();
if(mysqli_num_rows($result)>0){

		while($row = mysqli_fetch_assoc($result))
    		{   
    			$friendTemp = $row["friend_name"];
    			array_push($friends, $friendTemp);
    		}
	
	
	echo "true,";
	for ($x = 0; $x <= sizeof($friends); $x++) {
	   
            echo $friends[$x].",";
	  
	  
	   
	    
	    
}
}
else{
	echo "Retrieval of friends was not successful... :(";
}
?>