<?php
require "server_connection.php";

$user_email = $_POST["identifier_email"];
$user_pass = $_POST["identifier_password"];
$user_name = $_POST["identifier_name"];
$picture = $_POST["profile_picture"];

$mysql_query2 = "SELECT * FROM users WHERE email = '$user_email'";
$mysql_query3 = "SELECT * FROM users WHERE name = '$user_name'";
$result2 = mysqli_query($conn,$mysql_query2);
$result3 = mysqli_query($conn,$mysql_query3);

$query = "INSERT INTO users(email,password,name,picture) VALUES ('$user_email','$user_pass','$user_name','$picture')";
$query2 = "INSERT INTO countries(email,
Ireland,
Madagascar,
Bhutan,
AX,
XK,
Suriname,
CI,
RE,
BN,
TL,
FK,
Isle_of_Man,
Rwanda,
Burundi,
Equatorial_Guinea,
Gabon,
SS,
CG,
Haiti,
Vanuatu,
Togo,
Burkina_Faso,
Solomon_Islands,
CD,
Laos,
Belize,
North_Korea,
French_Guiana,
Eritrea,
Djibouti,
Central_African_Republic,
Lesotho,
Liberia,
Sierra_Leone,
Guinea,
Guinea_Bissau,
Malawi,
Svalbard_and_Jan_Mayen,
Greenland,
Romania,
Angola,
Republic_of_the_Congo,
Somalia,
Papua_New_Guinea,
Western_Sahara,
Mali,
Nigeria,
Chad,
Liechtenstein,
Maldives,
Sudan,
Zimbabwe,
Mauritania,
Mozambique,
Swaziland,
Tanzania,
Iraq,
Guyana,
Namibia,
Senegal,
Turkmenistan,
Afghanistan,
Andorra,
Fiji,
Uzbekistan,
Cameroon,
Cuba,
Faroe_Islands,
El_Salvador,
Caribbean,
Ethiopia,
Mongolia,
Puerto_Rico,
Samoa,
Myanmar,
Nicaragua,
Tajikistan,
Barbados,
Dominican_Republic,
Libya,
Panama,
Bahrain,
Benin,
Bolivia,
Ghana,
Montenegro,
Syria,
Ecuador,
Honduras,
Tunisia,
Botswana,
Cyprus,
Algeria,
Bahamas,
New_Caledonia,
Uganda,
Yemen,
Zambia,
Antarctica,
Paraguay,
Jamaica,
Bosnia_and_Herzegovina,
Vietnam,
Luxembourg,
Kenya,
Palestinian,
Nepala,
Niger,
Kuwait,
Hawaii,
Cambodia,
Uruguay,
Kyrgyzstan,
Saudi_Arabia,
Indonesia,
Azerbaijan,
United_Arab_Emirates,
Mauritius,
Alberta,
Morocco,
Albania,
South_Korea,
Kazakhstan,
Macedonia,
Venezuela,
Taiwan,
Qatar,
Jordan,
Iceland,
Guatemala,
Costa_Rica,
San_Marino,
Colombia,
Moldova,
Armenia,
Egypt,
Nepal,
Malta,
Lebanon,
Malaysia,
Serbia,
Peru,
Trinidad_and_Tobago,
Lithuania,
Estonia,
Georgia,
Iran,
Chile,
Latvia,
Thailand,
Slovenia,
Mexico,
Belarus,
Slovakia,
Sri_Lanka,
Croatia,
Philippines,
Turkey,
Italy,
South_Africa,
Bangladesh,
Hungary,
Pakistan,
Portugal,
Ukraine,
Greece,
Argentina,
Singapore,
Bulgaria,
Japan,
Czech_Republic,
China,
Oman,
Brazil,
Finland,
Norway,
Austria,
Denmark,
Belgium,
New_Zealand,
Spain,
Switzerland,
Russia,
Poland,
Israel,
Sweden,
Netherlands,
France,
Australia,
Canada,
India,
Germany,
United_Kingdom,
United_States,
Unknown_region
) VALUES ('$user_email',1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1)";

if(mysqli_num_rows($result2) == 0 && mysqli_num_rows($result3) == 0)
{
if(mysqli_query($conn,$query)){
	echo "<h2>Data Successfully Inserted!</h2>";
}
else{
	echo "<h2>Data was unable to be inserted into database :(.</h2>";
}
if(mysqli_query($conn,$query2)){
	echo "<h2>Data Successfully Inserted!</h2>";
}
else{
	echo "<h2>Data was unable to be inserted into database :(.</h2>";
}
}
?>

