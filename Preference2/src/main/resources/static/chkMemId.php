<?php
header("Content-Type: text/html; charset=utf-8");

$id=$_POST["AccountCheckID"];
 

if($id!=null && $id !="" )
{

$dbc = mysqli_connect("220.149.42.125","root","456111","preference")
		or die("DB Connection failed");
		    
	mysqli_query($dbc,"set session character_set_connection=utf8;");
  mysqli_query($dbc,"set session character_set_results=utf8;");
	mysqli_query($dbc,"set session character_set_client=utf8;");

	$sql="SELECT  count(*) as cmt FROM USERS WHERE ID='".$id."'";
	
	$result = mysqli_query($dbc,$sql);
	//	or die("DB query failed");
	    
	$result= mysqli_fetch_array($result);

	$Cmt = $result['cmt'];

	if($Cmt >0){
		echo "0";
	}else{
		echo "1";
	}
  
	mysqli_close($dbc)
		or die("DB close failed");

}else{
	echo "No data";
}
 
?>
    