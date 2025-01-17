<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Destruction app</title>
    <link href="navbar.css" rel="stylesheet">
    <link href="contactob.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    
</head>
<body>
    <div class="container-menu">
    	<div class="centered">
	        <button id="botonDesplegable">☰ Menú</button>
	        <nav id="menuDesplegable" class="menu-desplegable">
	            <ul>
	            <li><a href="RegistroServlet.jsp">
	                <button class="boton-menu">Inicio</button>
	            </a></li>
	            <li><a href="DarDeBaja.jsp">
	                <button class="boton-menu">Darse de baja</button>
	            </a></li>
	            </ul>
	        </nav>
	    </div>
	    <div class="contcerrarsesion">
	    	<a href="${pageContext.request.contextPath}/logout">
	        <input type="submit" id="cerrarsesion" value="Cerrar sesión">
	        </a>
	        <p class="mensaje">Usuario: ${sessionScope.nombre}</p>
	    </div>
    </div>
    
    <video class="video-container" id="video-bg" muted autoplay loop>
        <source src="espacio.mp4"
        type="video/mp4">
    </video>
    
    
    <div class="formularios">
	    <div class="form">
	    	<h1>Registro de usuario</h1>
	        <form action="RegistroServlet" method="POST">
	            <label>Nombre:</label>
	            <input type="text" id="nombre" name="nombre" class="etivertical" required>
	
	            <label>Correo electronico:</label>
	            <input type="text" id="correo" name="correo" class="etivertical" required>
	
	            <label>Contraseña:</label>
	            <input type="password" id="contras" name="contras" class="etipass" required>
	
	            <input type="submit" id="enviar" value="Registrarse">
	        </form>
	    </div>
    
    
	    <div class="form">
		    <h1>Iniciar sesión</h1>
		    <form action="ServletLogin" method="post">
		        <label>Nombre:</label>
		        <input type="text" name="nombre" class="etivertical" required>
		
		        <label>Contraseña:</label>
		        <input type="password" name="contras" class="etipass" required>
		
		        <input type="submit" id="enviar" value="Iniciar sesión"> 
		    </form>
	    </div>
    </div>
	
	
    
    <div class="pie-pagina">
        <h3 class="pie">Esta es una web creada por A.Munilla. 2023-2024</h3>
    </div>


</body>
</html>