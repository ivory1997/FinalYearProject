<?php
require "server_connection.php";
$user_email = $_POST["identifier_loginEmail"];
$username = $_POST["identifier_loginName"];
$mysql_query = "SELECT * FROM countries WHERE email = '$user_email'";
$result = mysqli_query($conn,$mysql_query);

if(mysqli_num_rows($result)>0){
	$row = mysqli_fetch_assoc($result);
	$email = $row["email"];
	$Ireland = $row["Ireland"];
	$Madagascar = $row["Madagascar"];
	$Bhutan = $row["Bhutan"];
	$AX = $row["AX"];
	$XK = $row["XK"];
	$Suriname = $row["Suriname"];
	$CI = $row["CI"];
	$RE = $row["RE"];
	$BN = $row["BN"];
	$TL = $row["TL"];
	$FK = $row["FK"];
	$Isle_of_Man = $row["Isle_of_Man"];
	$Rwanda = $row["Rwanda"];
	$Burundi = $row["Burundi"];
	$Equatorial_Guinea = $row["Equatorial_Guinea"];
	$Gabon = $row["Gabon"];
	$SS = $row["SS"];
	$CG = $row["CG"];
	$Haiti = $row["Haiti"];
	$Vanuatu = $row["Vanuatu"];
	$Togo = $row["Togo"];
	$Burkina_Faso = $row["Burkina_Faso"];
	$Solomon_Islands = $row["Solomon_Islands"];
	$CD = $row["CD"];
	$Laos = $row["Laos"];
	$Belize = $row["Belize"];
	$North_Korea = $row["North_Korea"];
	$French_Guiana = $row["French_Guiana"];
	$Eritrea = $row["Eritrea"];
	$Djibouti = $row["Djibouti"];
	$Central_African_Republic = $row["Central_African_Republic"];
	$Lesotho = $row["Lesotho"];
	$Liberia = $row["Liberia"];
	$Sierra_Leone = $row["Sierra_Leone"];
	$Guinea = $row["Guinea"];
	$Guinea_Bissau = $row["Guinea_Bissau"];
	$Malawi = $row["Malawi"];
	$Svalbard_and_Jan_Mayen = $row["Svalbard_and_Jan_Mayen"];
	$Greenland = $row["Greenland"];
	$Romania = $row["Romania"];
	$Angola = $row["Angola"];
	$Republic_of_the_Congo = $row["Republic_of_the_Congo"];
	$Somalia = $row["Somalia"];
	$Papua_New_Guinea = $row["Papua_New_Guinea"];
	$Western_Sahara = $row["Western_Sahara"];
    $Mali = $row["Mali"];
	$Nigeria = $row["Nigeria"];
	$Chad = $row["Chad"];
	$Liechtenstein = $row["Liechtenstein"];
	$Maldives = $row["Maldives"];
	$Sudan = $row["Sudan"];
	$Zimbabwe = $row["Zimbabwe"];
	$Mauritania = $row["Mauritania"];
	$Mozambique = $row["Mozambique"];
	$Swaziland = $row["Swaziland"];
	$Tanzania = $row["Tanzania"];
	$Iraq = $row["Iraq"];
	$Guyana = $row["Guyana"];
	$Namibia = $row["Namibia"];
	$Senegal = $row["Senegal"];
	$Turkmenistan = $row["Turkmenistan"];
	$Afghanistan = $row["Afghanistan"];
	$Andorra = $row["Andorra"];
	$Fiji = $row["Fiji"];
	$Uzbekistan = $row["Uzbekistan"];
	$Cameroon = $row["Cameroon"];
	$Cuba = $row["Cuba"];
	$Faroe_Islands = $row["Faroe_Islands"];
	$El_Salvador = $row["El_Salvador"];
	$Caribbean = $row["Caribbean"];
	$Ethiopia = $row["Ethiopia"];
	$Mongolia = $row["Mongolia"];
	$Puerto_Rico = $row["Puerto_Rico"];
	$Samoa = $row["Samoa"];
	$Myanmar = $row["Myanmar"];
	$Nicaragua = $row["Nicaragua"];
	$Tajikistan = $row["Tajikistan"];
	$Barbados = $row["Barbados"];
	$Dominican_Republic = $row["Dominican_Republic"];
	$Libya = $row["Libya"];
	$Panama = $row["Panama"];
	$Bahrain = $row["Bahrain"];
	$Benin = $row["Benin"];
	$Bolivia = $row["Bolivia"];
	$Ghana = $row["Ghana"];
	$Montenegro = $row["Montenegro"];
	$Syria = $row["Syria"];
	$Ecuador = $row["Ecuador"];
	$Honduras = $row["Honduras"];
	$Tunisia = $row["Tunisia"];
	$Botswana = $row["Botswana"];
	$Cyprus = $row["Cyprus"];
	$Algeria = $row["Algeria"];
	$Bahamas = $row["Bahamas"];
	$New_Caledonia = $row["New_Caledonia"];
	$Uganda = $row["Uganda"];
	$Yemen = $row["Yemen"];
	$Zambia = $row["Zambia"];
	$Antarctica = $row["Antarctica"];
	$Paraguay = $row["Paraguay"];
	$Jamaica = $row["Jamaica"];
	$Bosnia_and_Herzegovina = $row["Bosnia_and_Herzegovina"];
	$Vietnam = $row["Vietnam"];
	$Luxembourg = $row["Luxembourg"];
	$Kenya = $row["Kenya"];
	$Palestinian = $row["Palestinian"];
	$Nepala = $row["Nepala"];
	$Niger = $row["Niger"];
	$Kuwait = $row["Kuwait"];
	$Hawaii = $row["Hawaii"];
	$Cambodia = $row["Cambodia"];
	$Uruguay = $row["Uruguay"];
	$Kyrgyzstan = $row["Kyrgyzstan"];
	$Saudi_Arabia = $row["Saudi_Arabia"];
	$Indonesia =  $row["Indonesia"];
	$Azerbaijan = $row["Azerbaijan"];
	$United_Arab_Emirates = $row["United_Arab_Emirates"];
	$Mauritius = $row["Mauritius"];
	$Alberta = $row["Alberta"];
	$Morocco = $row["Morocco"];
	$Albania = $row["Albania"];
	$South_Korea = $row["South_Korea"];
	$Kazakhstan = $row["Kazakhstan"];
	$Macedonia = $row["Macedonia"];
	$Venezuela = $row["Taiwan"];
	$Taiwan = $row["Taiwan"];
	$Qatar = $row["Qatar"];
	$Jordan = $row["Jordan"];
	$Iceland = $row["Iceland"];
	$Guatemala = $row["Guatemala"];
	$Costa_Rica = $row["Costa_Rica"];
	$San_Marino = $row["San_Marino"];
	$Colombia = $row["Colombia"];
	$Moldova = $row["Moldova"];
	$Armenia = $row["Armenia"];
	$Egypt = $row["Egypt"];
	$Nepal = $row["Nepal"];
	$Malta = $row["Malta"];
	$Lebanon = $row["Lebanon"];
	$Malaysia = $row["Malaysia"];
	$Serbia = $row["Serbia"];
	$Peru = $row["Peru"];
	$Trinidad_and_Tobago = $row["Trinidad_and_Tobago"];
	$Lithuania = $row["Lithuania"];
	$Estonia = $row["Estonia"];
	$Georgia = $row["Georgia"];
	$Iran = $row["Iran"];
	$Chile = $row["Chile"];
	$Latvia = $row["Latvia"];
	$Thailand = $row["Thailand"];
	$Slovenia = $row["Slovenia"];
	$Mexico = $row["Mexico"];
	$Belarus = $row["Belarus"];
	$Slovakia = $row["Slovakia"];
	$Sri_Lanka = $row["Sri_Lanka"];
	$Croatia = $row["Croatia"];
	$Philippines = $row["Philippines"];
	$Bangladesh = $row["Bangladesh"];
	$Turkey = $row["Turkey"];
	$Italy = $row["Italy"];
	$South_Africa = $row["South_Africa"];
	$Hungary = $row["Hungary"];
	$Pakistan = $row["Pakistan"];
	$Portugal = $row["Portugal"];
	$Ukraine = $row["Ukraine"];
	$Greece = $row["Greece"];
	$Argentina = $row["Argentina"];
	$Singapore = $row["Singapore"];
	$Bulgaria = $row["Bulgaria"];
	$Japan = $row["Japan"];
	$Czech_Republic  = $row["Czech_Republic"];
	$China = $row["China"];
	$Oman = $row["Oman"];
	$Brazil = $row["Brazil"];
	$Finland = $row["Finland"];
	$Norway = $row["Norway"];
	$Austria = $row["Austria"];
	$Denmark = $row["Denmark"];
	$Belgium = $row["Belgium"];
	$New_Zealand = $row["New_Zealand"];
	$Spain = $row["Spain"];
	$Switzerland = $row["Switzerland"];
	$Russia = $row["Russia"];
	$Poland = $row["Poland"];
	$Israel = $row["Israel"];
	$Sweden = $row["Sweden"];
	$Netherlands = $row["Netherlands"];
	$France = $row["France"];
	$Australia = $row["Australia"];
	$Canada = $row["Canada"];
	$India = $row["India"];
	$Germany = $row["Germany"];
	$United_Kingdom = $row["United_Kingdom"];
	$United_States = $row["United_States"];
	$Unknown = $row["Unknown_region"];

	
	
	echo "true,".$email.",".$username.",Ireland,".$Ireland.",Madagascar,".$Madagascar.",Bhutan,".$Bhutan.",AX,".$AX.",XK,".$XK.",Suriname,".$Suriname.",CI,".$CI.",RE,".$RE.",BN,".$BN.",TL,".$TL.",FK,".$FK.",Isle of Man,".$Isle_of_Man.",Rwanda,".$Rwanda.",Burundi,".$Burundi.",Equatorial Guinea,".$Equatorial_Guinea.",Gabon,".$Gabon.",SS,".$SS.",CG,".$CG.",Haiti,".$Haiti.",Vanuatu,".$Vanuatu.",Togo,".$Togo.",Burkina Faso,".$Burkina_Faso.",Solomon Islands,".$Solomon_Islands.",CD,".$CD.",Laos,".$Laos.",Belize,".$Belize.",North Korea,".$North_Korea.",French Guiana,".$French_Guiana.",Eritrea,".$Eritrea.",Djibouti,".$Djibouti.",Central African Republic,".$Central_African_Republic.",Lesotho,".$Lesotho.",Liberia,".$Liberia.",Sierra Leone,".$Sierra_Leone.",Guinea,".$Guinea.",Guinea-Bissau,".$Guinea_Bissau.",Malawi,".$Malawi.",Svalbard and Jan Mayen,".$Svalbard_and_Jan_Mayen.",Greenland,".$Greenland.",Romania,".$Romania.",Angola,".$Angola.",Republic of the Congo,".$Republic_of_the_Congo.",Somalia,".$Somalia.",Papua New Guinea,".$Papua_New_Guinea.",Western Sahara,".$Western_Sahara.",Mali,".$Mali.",Nigeria,".$Nigeria.",Chad,".$Chad.",Liechtenstein,".$Liechtenstein.",Maldives,".$Maldives.",Sudan,".$Sudan.",Zimbabwe,".$Zimbabwe.",Mauritania,".$Mauritania.",Mozambique,".$Mozambique.",Swaziland,".$Swaziland.",Tanzania,".$Tanzania.",Iraq,".$Iraq.",Guyana,".$Guyana.",Namibia,".$Namibia.",Senegal,".$Senegal.",Turkmenistan,".$Turkmenistan.",Afghanistan,".$Afghanistan.",Andorra,".$Andorra.",Fiji,".$Fiji.",Uzbekistan,".$Uzbekistan.",Cameroon,".$Cameroon.",Cuba,".$Cuba.",Faroe Islands,".$Faroe_Islands.",El Salvador,".$El_Salvador.",Caribbean,".$Caribbean.",Ethiopia,".$Ethiopia.",Mongolia,".$Mongolia.",Puerto Rico,".$Puerto_Rico.",Samoa,".$Samoa.",Myanmar,".$Myanmar.",Nicaragua,".$Nicaragua.",Tajikistan,".$Tajikistan.",Barbados,".$Barbados.",Dominican Republic,".$Dominican_Republic.",Libya,".$Libya.",Panama,".$Panama.",Bahrain,".$Bahrain.",Benin,".$Benin.",Bolivia,".$Bolivia.",Ghana,".$Ghana.",Montenegro,".$Montenegro.",Syria,".$Syria.",Ecuador,".$Ecuador.",Honduras,".$Honduras.",Tunisia,".$Tunisia.",Botswana,".$Botswana.",Cyprus,".$Cyprus.",Algeria,".$Algeria.",Bahamas,".$Bahamas.",New Caledonia,".$New_Caledonia.",Uganda,".$Uganda.",Yemen,".$Yemen.",Zambia,".$Zambia.",Antarctica,".$Antarctica.",Paraguay,".$Paraguay.",Jamaica,".$Jamaica.",Bosnia and Herzegovina,".$Bosnia_and_Herzegovina.",Vietnam,".$Vietnam.",Luxembourg,".$Luxembourg.",Kenya,".$Kenya.",Palestinian,".$Palestinian.",Nepala,".$Nepala.",Niger,".$Niger.",Kuwait,".$Kuwait.",Hawaii,".$Hawaii.",Cambodia,".$Cambodia.",Uruguay,".$Uruguay.",Kyrgyzstan,".$Kyrgyzstan.",Saudi Arabia,".$Saudi_Arabia.",Indonesia,".$Indonesia.",Azerbaijan,".$Azerbaijan.",United Arab Emirates,".$United_Arab_Emirates.",Mauritius,".$Mauritius.",Alberta,".$Alberta.",Morocco,".$Morocco.",Albania,".$Albania.",South Korea,".$South_Korea.",Kazakhstan,".$Kazakhstan.",Macedonia,".$Macedonia.",Venezuela,".$Venezuela.",Taiwan,".$Taiwan.",Qatar,".$Qatar.",Jordan,".$Jordan.",Iceland,".$Iceland.",Guatemala,".$Guatemala.",Costa Rica,".$Costa_Rica.",San Marino,".$San_Marino.",Colombia,".$Colombia.",Moldova,".$Moldova.",Armenia,".$Armenia.",Egypt,".$Egypt.",Nepal,".$Nepal.",Malta,".$Malta.",Lebanon,".$Lebanon.",Malaysia,".$Malaysia.",Serbia,".$Serbia.",Peru,".$Peru.",Trinidad and Tobago,".$Trinidad_and_Tobago.",Lithuania,".$Lithuania.",Estonia,".$Estonia.",Georgia,".$Georgia.",Iran,".$Iran.",Chile,".$Chile.",Latvia,".$Latvia.",Thailand,".$Thailand.",Slovenia,".$Slovenia.",Mexico,".$Mexico.",Belarus,".$Belarus.",Slovakia,".$Slovakia.",Sri Lanka,".$Sri_Lanka.",Croatia,".$Croatia.",Philippines,".$Philippines.",Bangladesh,".$Bangladesh.",Turkey,".$Turkey.",Italy,".$Italy.",South Africa,".$South_Africa.",Hungary,".$Hungary.",Pakistan,".$Pakistan.",Portugal,".$Portugal.",Ukraine,".$Ukraine.",Greece,".$Greece.",Argentina,".$Argentina.",Singapore,".$Singapore.",Bulgaria,".$Bulgaria.",Japan,".$Japan.",Czech Republic ,".$Czech_Republic .",China,".$China.",Oman,".$Oman.",Brazil,".$Brazil.",Finland,".$Finland.",Norway,".$Norway.",Austria,".$Austria.",Denmark,".$Denmark.",Belgium,".$Belgium.",New Zealand,".$New_Zealand.",Spain,".$Spain.",Switzerland,".$Switzerland.",Russia,".$Russia.",Poland,".$Poland.",Israel,".$Israel.",Sweden,".$Sweden.",Netherlands,".$Netherlands.",France,".$France.",Australia,".$Australia.",Canada,".$Canada.",India,".$India.",Germany,".$Germany.",United Kingdom,".$United_Kingdom.",United States,".$United_States.",Unknown,".$Unknown;
}
else{
	echo "Retrieval of countries was not successful... :(";
}
?>