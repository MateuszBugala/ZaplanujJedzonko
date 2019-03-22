<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../../header.jsp" %>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">

        <%@include file="../../sidepanel.jsp" %>

        <div class="m-4 p-3 width-medium">
            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding">
                        <h3 class="color-header text-uppercase">LISTA PLANÓW</h3>
                    </div>
                    <div class="col d-flex justify-content-end mb-2 noPadding">
                        <a href='<c:url value="/app/plan/add"/>' class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Dodaj plan</a>
                    </div>
                </div>

                <c:if test ="${not empty param.deleted}">
                    <h4 style="color: red; text-align: -moz-center"><span>Plan został usunięty</span></h4>
                </c:if>
                <c:if test ="${not empty param.blocked}">
                    <h3 style="color: red; text-align: -moz-center">Nie można usunąć planu ponieważ zawiera posiłki</h3>
                </c:if>

                <div class="schedules-content">
                    <table class="table border-bottom">
                        <thead>
                        <tr class="d-flex">
                            <th class="col-1">ID</th>
                            <th class="col-2">NAZWA</th>
                            <th class="col-7">OPIS</th>
                            <th class="col-2 center">AKCJE</th>
                        </tr>
                        </thead>
                        <tbody class="text-color-lighter">

                        <c:if test="${plans.size()>0}">
                        <c:forEach begin="0" step="1" end="${plans.size()-1}" var="number" varStatus="item">

                            <tr class="d-flex">
                                <td class="col-1">${item.count}</td>
                                <td class="col-2">${plans.get(number).getName()}</td>
                                <td class="col-7">
                                        ${plans.get(number).getDescription()}
                                </td>
                                <td class="col-2 d-flex align-items-center justify-content-center flex-wrap">

                                    <a href='<c:url value="#confirmation${plans.get(number).getId()}">
                                </c:url>' class="btn btn-danger rounded-0 text-light m-1">Usuń</a>

                                    <a href='<c:url value="/app/plan/details">
                                <c:param name = "planId" value = "${plans.get(number).getId()}"/>
                                </c:url>' class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>

                                    <a href='<c:url value="/app/plan/edit">
                                <c:param name = "planId" value = "${plans.get(number).getId()}"/>
                                </c:url>' class="btn btn-warning rounded-0 text-light m-1">Edytuj</a>

                                </td>
                            </tr>

                            <div id="confirmation${plans.get(number).getId()}" class="modalDialog">
                                <div style="color: #00d9ff">
                                    <a href="#close" title="Close" class="close">X</a>
                                    <h3 style="text-align: -moz-center">Usuwanie planu</h3>
                                    <p></p>
                                    <p style="margin-top: 50px; margin-bottom: 50px">Czy na pewno chcesz usunąć ten plan?<br>(tej
                                        operacji nie da się cofnąć)</p>
                                    <p></p>
                                    <a href="/app/plan/delete?planId=${plans.get(number).getId()}"
                                       class="btn btn-danger rounded-0 text-light m-1">Tak, usuń</a>
                                    <a href="#" class="btn btn-info rounded-0 text-light m-1">Nie, wróć</a>
                                </div>
                            </div>

                        </c:forEach>
                        </c:if>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>


<%@include file="../../footer.jsp" %>

