<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../../header.jsp" %>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">

        <%@include file="../../sidepanel.jsp" %>

        <div class="m-4 p-3 width-medium text-color-darker">
            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <div class="mt-4 ml-4 mr-4">
                    <div class="row border-bottom border-3">

                        <div class="col"><h3 class="color-header text-uppercase">Edycja przepisu</h3></div>
                        <div class="col d-flex justify-content-end mb-2"><button onclick="javascript:document.formularz.submit();" type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz</button></div>
                    </div>

                    <c:if test ="${not empty preparationTimeError}">
                        <span style="color: red; text-align: -moz-center">Czas przygotowania jest nieprawidłowy, spróbuj jeszcze raz</span>
                    </c:if>


                    <form action="/app/recipe/edit" method="post" name="formularz">


                    <table class="table borderless">
                        <tbody>
                        <tr class="d-flex">
                            <th scope="row" class="col-2">Nazwa Przepisu</th>
                            <td class="col-7">
                                <input type="text" class="form-control" value="${recipeFromDB.name}" name="recipeName">
                            </td>
                        </tr>

                        <tr class="d-flex">
                            <th scope="row" class="col-2">Opis przepisu</th>
                            <td class="col-7"> <textarea class="w-100 p-1" rows="5" name="recipeDescription">${recipeFromDB.description}</textarea></td>
                        </tr>

                        <tr class="d-flex">
                            <th scope="row" class="col-2">Przygotowanie (minuty)</th>
                            <td class="col-3">
                                <input class="p-1" type="number" value="${recipeFromDB.preparationTime}" name="recipePreparationTime">
                            </td>
                        </tr>

                        </tbody>
                    </table>

                    <div class="row d-flex">
                        <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Sposób przygotowania</h3></div>
                        <div class="col-2"></div>
                        <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Składniki</h3></div>
                    </div>
                    <div class="row d-flex">
                        <div class="col-5 p-4">
                            <textarea class="w-100 p-1" rows="10" name="recipePreparation">${recipeFromDB.preparation}</textarea>
                        </div>
                        <div class="col-2"></div>

                        <div class="col-5 p-4">
                                    <textarea class="w-100 p-1" rows="10" name="recipeIngredients">${recipeFromDB.ingredients}</textarea>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <input name="recipeId" hidden value="${recipeFromDB.id}">
        </form>

    </div>
</section>


<%@include file="../../footer.jsp" %>

