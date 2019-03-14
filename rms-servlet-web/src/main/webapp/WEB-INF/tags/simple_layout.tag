<!doctype html>
<%@tag description="Simple Template" pageEncoding="UTF-8"%>
<%@attribute name="body_area" fragment="true"%>
<html lang="en">
<head>
<meta charset="utf-8">

<title>RMS</title>
<meta name="description" content="Index">
<meta name="author" content="Mitrais">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
<link rel="stylesheet" href="css/styles.css?v=1.0">

<!--[if lt IE 9]>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.js"></script>
  <![endif]-->
</head>

<body>
	<div class="demo-layout-transparent mdl-layout mdl-js-layout"
		style="background-color: blue;">
		<!-- 	<div class="mdl-layout mdl-js-layout mdl-color--grey-100"> -->
		<header class="mdl-layout__header mdl-layout__header--transparent">
			<div class="mdl-layout__header-row">
				<!-- Title -->
				<span class="mdl-layout-title">RMS</span>
				<!-- Add spacer, to align navigation to the right -->
				<div class="mdl-layout-spacer"></div>
				<!-- Navigation -->
				<nav class="mdl-navigation">
					<a class="mdl-navigation__link"
						href="<%=request.getContextPath()%>/users/list">Users</a> <a
						class="mdl-navigation__link"
						href="<%=request.getContextPath()%>/users/form">Add User</a> <a
						class="mdl-navigation__link"
						href="<%=request.getContextPath()%>/logout">Logout</a> <a
						class="mdl-navigation__link" href="">Link</a>
				</nav>
			</div>
		</header>
		<div class="mdl-layout__drawer">
			<span class="mdl-layout-title">RMS</span>
			<nav class="mdl-navigation">
				<a class="mdl-navigation__link"
					href="<%=request.getContextPath()%>/users/list">Users</a> <a
					class="mdl-navigation__link"
					href="<%=request.getContextPath()%>/users/form">Add User</a> <a
					class="mdl-navigation__link"
					href="<%=request.getContextPath()%>/logout">Logout</a> <a
					class="mdl-navigation__link" href="">Link</a>
			</nav>
		</div>

		<main class="mdl-layout__content"> <jsp:invoke
			fragment="body_area" /> </main>
	</div>
	<script src="js/scripts.js"></script>
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
		$(".delete_class").click(function(event) {
			
			var r = confirm("Are you sure to delete this record?");
			if (!r) {
				event.preventDefault();
			}
		});
	</script>
</body>
</html>
