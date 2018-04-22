<?php
header("Content-Type: text/html; charset=utf-8");

$id=$_POST["LoginID"];
$pwd=$_POST["LoginPass"];

if(($id!=null && $id !="") && ($pwd!=null && $pwd !=""))
{ 

$dbc = mysqli_connect("220.149.42.125","root","456111","preference")
		or die("DB Connection failed");
		    
	mysqli_query($dbc,"set session character_set_connection=utf8;");
  mysqli_query($dbc,"set session character_set_results=utf8;");
	mysqli_query($dbc,"set session character_set_client=utf8;");

	$sql="SELECT COUNT(*) as CMT  FROM USERS WHERE ID='".$id."'";
	
	if($result = mysqli_query($dbc,$sql)){
	
    $result= mysqli_fetch_array($result);
	$cmt = $result['CMT'];
	if($cmt <1){
	 echo "0";
	}else{
	
		$sql="SELECT ID, PASSWORD  FROM USERS WHERE ID='".$id."' AND PASSWORD='".$pwd."'";
			$result = mysqli_query($dbc,$sql);
		   if($result= mysqli_fetch_array($result)){
			 echo "2";
			}else{
			 echo "1";
			}

	}
    
		
  
	}else{
		echo ("query failed");
	}

	mysqli_close($dbc)
		or die("DB close failed");

}else{
	echo "No data";
}
 
?>
