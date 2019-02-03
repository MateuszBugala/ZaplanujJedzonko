<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../../header.jsp" %>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">

        <%@include file="../../sidepanel.jsp" %>

        <div class="m-4 p-3 width-medium ">
            <div class="dashboard-content border-dashed p-3 m-4">
                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding">
                        <h3 class="color-header text-uppercase">SZCZEGÓŁY PLANU</h3>
                    </div>
                    <div class="col d-flex justify-content-end mb-2 noPadding">
                        <a href="/app/plan/list/" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
                    </div>
                </div>

                <div class="schedules-content">
                    <div class="schedules-content-header">
                        <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Nazwa planu
                                </span>
                            <div class="col-sm-10">
                                <p class="schedules-text">${plan.name}</p>
                            </div>
                        </div>
                        <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Opis planu
                                </span>
                            <div class="col-sm-10">
                                <p class="schedules-text">${plan.description}</p>
                            </div>
                        </div>
                    </div>

                    <c:if test ="${not empty param.deleted}">
                        <h4 style="color: red; text-align: -moz-center"><span>Posiłek został usunięty z planu</span></h4>
                    </c:if>

                    <table class="table">

                        <c:forEach items="${days}" var="day">

                        <thead>
                        <tr class="d-flex">
                            <th class="col-2">${day}</th>
                            <th class="col-7"></th>
                            <th class="col-1"></th>
                            <th class="col-2"></th>
                        </tr>
                        </thead>


                        <tbody class="text-color-lighter">
                            <c:forEach items="${planDetails}" var="meal">
                                <c:if test="${meal.dayName == day}">

                                <tr class="d-flex">
                                    <td class="col-2">${meal.mealName}</td>
                                    <td class="col-7">${meal.recipeName}</td>
                                    <td class="col-3 center">
                                        <a class="btn btn-danger rounded-0 text-light m-1"
                                           href="#confirmation${meal.recipePlanId}">Usuń</a>

                                    </td>
                                    <%--aktywując poniższy przycisk trzeba zmienić na "col-1" dla przycisku usuń--%>
                                    <%--<td class="col-2 center">--%>
                                        <%--<a href="#" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>--%>
                                    <%--</td>--%>
                                </tr>

                                </c:if>

                                <div id="confirmation${meal.recipePlanId}" class="modalDialog">
                                    <div style="color: #00d9ff">
                                        <a href="#close" title="Close" class="close">X</a>
                                        <h3 style="text-align: -moz-center">Usuwanie posiłku</h3>
                                        <p></p>
                                        <p style="margin-top: 50px; margin-bottom: 50px">Czy na pewno chcesz usunąć ten posiłek z planu<br>(tej
                                            operacji nie da się cofnąć)</p>
                                        <p></p>
                                        <a href="/app/recipe/plan/delete?planId=${param.planId}&recipePlanId=${meal.recipePlanId}"
                                           class="btn btn-danger rounded-0 text-light m-1">Tak, usuń</a>
                                        <a href="#" class="btn btn-info rounded-0 text-light m-1">Nie, wróć</a>
                                    </div>
                                </div>


                            </c:forEach>
                        </tbody>



                        </c:forEach>

                    </table>

                </div>
            </div>
        </div>
    </div>
</section>

<%@include file="../../footer.jsp" %>

