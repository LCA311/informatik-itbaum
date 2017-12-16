<?php

  require_once('../dbconfig.php');

  $db = new mysqli(dbhost, dbuser, dbpass, dbname);

  if ($db->connect_error)
    die("-connection failed: ".$db->connect_error);

  $subject = $db->real_escape_string($_GET['subject']);

  $sql = "SELECT name, posX, posY FROM Nodes WHERE thema=(SELECT id WHERE name = '".$subject."')";
  $result = $db->query($sql);

  if($result === false)
    die("-ERR");

  while($row = $result->fetch_assoc()) {
    echo $row['name']."_;_".$row['posX']."_;_".$row['posY']."_;;_";
  }

?>
