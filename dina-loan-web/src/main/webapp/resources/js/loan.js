/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var disabledDays = ["2015-12-01", "2016-01-15"];
function disableAllTheseDays(date) {
     
    var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();
    for (i = 0; i < disabledDays.length; i++) {
        if($.inArray((m+1) + '-' + d + '-' + y,disabledDays) != -1) {
            return [false];
        }
    }
    return [true];
}