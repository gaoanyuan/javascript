<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<@security.authorize ifAnyGranted="ROLE_ADMIN">
    ROLE_USER
</@security.authorize>
<h1>${userName}</h1>111
<a href="/fmg-web/j_spring_security_logout">Log Out</a>
