<%--
  Created by IntelliJ IDEA.
  User: katty
  Date: 03.11.2020
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="popup2" class="popup2">
    <div class="popup2_body">
        <div class="popup2_content">
            <a href="#" class="popup2_close">Х</a>
            <div class="popup2_title">Тогда скорее поделитесь своими ассоциациями :)</div>
            <div class="popup2_text">
                <div class="new2" id="new2">
                    <div class="info2" id="info2">
                        <form  action="send-word" method="post">
                            <p><textarea oninput="check_word()" name="comment2"
                                         id="comment2"></textarea></p>

                            <p>
                                <input class="button" type="submit" name="submit"
                                       id="submit" value="Отправить" disabled/>
                            </p>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
