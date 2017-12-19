<?php

	require_once('../dbconfig.php');

	$db = new mysqli(dbhost, dbuser, dbpass, dbname);

	if ($db->connect_error)
		die("-connection failed: ".$db->connect_error);

	$text = $db->real_escape_string($_GET['text']);
	$posX = $db->real_escape_string($_GET['x']);
	$posY = $db->real_escape_string($_GET['y']);
	$thema = $db->real_escape_string($_GET['thema']);

	$sql = "INSERT INTO Thema VALUES (null, '".$thema."') ON DUPLICATE KEY UPDATE name=name";
	$sql2 = "INSERT INTO Nodes VALUES (null, '".$text."', ".$posX.", ".$posY.", (SELECT id FROM Thema WHERE name='".$thema."')) ON DUPLICATE KEY UPDATE posX=posX";

	$result = $db->query($sql);

	if($result === false)
		die("-ERR 1");

	$result = $db->query($sql2);

	if($result === false)
		die("-ERR 2");

	echo "+OK";

?>
