<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../../header.jsp" %>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">

        <%@include file="../../sidepanel.jsp" %>

        <div class="m-4 p-3 width-medium">
            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding"><h3 class="color-header text-uppercase">Lista Przepisów</h3></div>
                    <div class="col noPadding d-flex justify-content-end mb-2"><a href="/app/recipe/add"
                                                                                  class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Dodaj
                        przepis</a></div>
                </div>


                <table class="table border-bottom schedules-content">


                    <thead>
                    <tr class="d-flex text-color-darker">
                        <th scope="col" class="col-1">ID</th>
                        <th scope="col" class="col-2">NAZWA</th>
                        <th scope="col" class="col-7">OPIS</th>
                        <th scope="col" class="col-2 center">AKCJE</th>
                    </tr>
                    </thead>
                    <c:forEach items="${recipes}" var="recipe" varStatus="item">
                        <tbody class="text-color-lighter">
                        <tr class="d-flex">
                            <th scope="row" class="col-1">${item.count}</th>
                            <td class="col-2">${recipe.name}</td>
                            <td class="col-7">${recipe.description}</td>
                            <td class="col-2 d-flex align-items-right justify-content-center flex-wrap">
                                    <%--pierwotny przycisk:--%>
                                    <%--<a href="/app/RecipeDelFromListConfirm?recipeId=${recipe.id}"--%>
                                    <%--class="btn btn-danger rounded-0 text-light m-1">Usuń</a>--%>

                                    <%--modal 2--%>
                                <a class="btn btn-danger rounded-0 text-light m-1"
                                   href="#confirmation${recipe.id}">Usuń</a>
                                <a href="/app/recipe/details?recipeId=${recipe.id}"
                                   class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                                <a href="/app/recipe/edit?recipeId=${recipe.id}"
                                   class="btn btn-warning rounded-0 text-light m-1">Edytuj</a>
                            </td>
                        </tr>
                        </tbody>

                        <%--modal 2--%>
                        <div id="confirmation${recipe.id}" class="modalDialog">
                            <div style="color: #00d9ff">
                                <a href="#close" title="Close" class="close">X</a>
                                <h3 style="text-align: -moz-center">Usuwanie przepisu</h3>
                                <p></p>
                                <p style="margin-top: 50px; margin-bottom: 50px">Czy na pewno chcesz usunąć ten przepis?<br>(tej
                                    operacji nie da się cofnąć)</p>
                                <p></p>
                                <a href="/app/RecipeDelFromListConfirm?recipeId=${recipe.id}"
                                   class="btn btn-danger rounded-0 text-light m-1">Tak, usuń</a>
                                <a href="#" class="btn btn-info rounded-0 text-light m-1">Nie, wróć</a>
                            </div>
                        </div>
                        <%--modal 2--%>

                    </c:forEach>
                </table>


                <%--modal1 (problem z zasłanianiem wszystkich elementów tabeli)--%>

                <%--<div class="box">--%>
                <%--<a class="btn btn-danger rounded-0 text-light m-1" href="#confirmation">Usuń</a>--%>
                <%--</div>--%>
                <%--<div id="confirmation" class="overlay">--%>
                <%--<div class="popup">--%>
                <%--<h3 style="text-align: -moz-center">Usuwanie przepisu</h3>--%>
                <%--<a class="close" href="#">&times; </a>--%>
                <%--<div class="content">--%>
                <%--Czy na pewno chcesz usunąć ten przepis?--%>
                <%--<br>(tej operacji nie da się cofnąć)--%>
                <%--<br><br>--%>
                <%--<a href="/app/RecipeDelFromListConfirm?recipeId=${recipe.id}"--%>
                <%--class="btn btn-danger rounded-0 text-light m-1">Tak, usuń</a>--%>
                <%--<a href="#" class="btn btn-info rounded-0 text-light m-1">Nie, wróć</a>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--</div>--%>

            </div>
        </div>

    </div>
</section>

<%@include file="../../footer.jsp" %>

