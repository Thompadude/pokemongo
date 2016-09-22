/* posts.js */

'use strict';

console.log("It is read");

$(document).ready(function () {
    $(".parentPost__commentsBtn").click(function () {
        console.log("toggling!");
        $(this).next().slideToggle(200);
        $(this).parent().next().slideToggle(200);
    });
});

