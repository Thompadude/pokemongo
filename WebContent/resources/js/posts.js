/* posts.js */

'use strict';

$(document).ready(function () {
    $(".button--comments").click(function () {

        var hider = $(this).parent().parent().parent().next();

        hider.slideToggle(200);
        hider.next().slideToggle(200);
    });
});