<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
 
<t:simple_layout>
 
 
<jsp:attribute name="body_area">
  <div class="mdl-card mdl-shadow--6dp">
			<div class="mdl-card__title mdl-color--primary mdl-color-text--white">
				<h2 class="mdl-card__title-text">User Form</h2>
			</div>
			<div class="mdl-card__actions mdl-card--border">
					<p style="color:red;">${warningmessage}</p>
			</div>
			<form action="form" method="post">
			<input type="hidden" name="id" value="${user.id}" />
				<div class="mdl-card__supporting-text">
			
					<div class="mdl-textfield mdl-js-textfield">
						<input class="mdl-textfield__input" type="text" id="username" name="username" value="${user.userName}" />
						<label class="mdl-textfield__label" for="username">Username</label>
					</div>
					<div class="mdl-textfield mdl-js-textfield">
						<input class="mdl-textfield__input" type="password" id="userpass" name="userpass" value="${user.password}" />
						<label class="mdl-textfield__label" for="userpass">Password</label>
					</div>

				</div>
				<div class="mdl-card__actions mdl-card--border">
					<button
						class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Save</button>
				</div>
			</form>
		</div>
</jsp:attribute>
 
</t:simple_layout>