<?php
header("Content-Type: text/html; charset=utf-8");
/*


if($idx!=null && $node!=null && $geofence!=null)
{
	$dbc = mysqli_connect("localhost","root","apmsetup","beacon_samda")
		or die("DB Connection failed");
		    
	mysqli_query($dbc,"set session character_set_connection=utf8;");
	mysqli_query($dbc,"set session character_set_results=utf8;");
	mysqli_query($dbc,"set session character_set_client=utf8;");

	$sql = sprintf("insert into geo_data(idx,node,geofence) values (%s,%s,%s)", $idx,$node,$geofence);
	$result = mysqli_query($dbc,$sql)
		or die("DB query failed");


	mysqli_close($dbc)
		or die("DB close failed");

	echo "Insert OK";
}
else
{
	echo "No data";
}
*/

$id=$_POST["AccountID"];
$pwd=$_POST["AccountPass"];
$name=$_POST["AccountName"];
$sex=$_POST["AccountSex"];
$byear=$_POST["AccountBirthYear"];
//$bmonth=$_POST["AccountBirthMonth"];
$birth=$byear;
$school=$_POST["AccountSchool"];
$job=$_POST["AccountJob"];
$hobby=$_POST["AccountHobby"];
$AccountRegion = $_POST['AccountRegion'];


if($id!=null && $pwd!=null && $name!=null)
{

$dbc = mysqli_connect("220.149.42.125","root","456111","preference")
		or die("DB Connection failed");
		    
	mysqli_query($dbc,"set session character_set_connection=utf8;");
  mysqli_query($dbc,"set session character_set_results=utf8;");
	mysqli_query($dbc,"set session character_set_client=utf8;");

	$sql = "insert into USERS(ID,PASSWORD,NAME,SEX,BIRTH,EDUCATION,JOB,HOBBY,REGION) ";
	$sql .= "VALUES('".$id."','".$pwd."','".$name."','".$sex."','".$birth."','".$school."','".$job."','".$hobby."','".$AccountRegion."')" ;
	//$sql .= "VALUES('3','2','테스트','ㅇㅇ','123-5','255','ㄹㄴㄹ','ㄴㄹㅇㄴ')" ;
	if($result = mysqli_query($dbc,$sql)){
		echo "1";
	}else{
		echo "0";
	}

		
//	or die("DB query failed");

	mysqli_close($dbc);
//	or die("DB close failed");

	//echo "1";

}else{
	echo "No data";
}
 
?>
