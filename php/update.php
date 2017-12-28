<?php

	require_once('../dbconfig.php');

	$db = new mysqli(dbhost, dbuser, dbpass, dbname);

	if ($db->connect_error)
		die("-connection failed: ".$db->connect_error);
		
	$text = $db->real_escape_string($_GET['text']);
	$subject = $db->real_escape_string($_GET['subject']);
	$posX = $db->real_escape_string($_GET['x']);
	$posY = $db->real_escape_string($_GET['y']);

	if(strcmp($posX, " "))
		$posX = 0;

	if(strcmp($posY, " "))
		$posY = 0;
	
	$sql = "UPDATE Nodes SET name = '".$text."' WHERE posX=".$posX." AND posY=".$posY." AND thema = (SELECT id FROM Thema WHERE name='".$subject."')";

echo $sql;
	
	$result = $db->query($sql);
	
	if($result === false)
		die("-ERR");
		
	echo "+OK";

?>