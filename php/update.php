<?php

	require_once('../dbconfig.php');

	$db = new mysqli(dbhost, dbuser, dbpass, dbname);

	if ($db->connect_error)
		die("-connection failed: ".$db->connect_error);
		
	$text = $db->real_escape_string($_GET['text']);
	$subject = $db->real_escape_string($_GET['subject']);
	$posX = $db->real_escape_string($_GET['x']);
	$posY = $db->real_escape_string($_GET['y']);
	
	$sql = "UPDATE Nodes SET (name) VALUES ('".$text."') WHERE posX=".$posX." AND posY=".$posY." AND thema = '"$subject."'";
	
	$result = $db->query($sql);
	
	if($result === false)
		die("-ERR");
		
	echo "+OK";

?>