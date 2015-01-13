/* jQuery Datepicker with Apple-class styling.
 * Based on the capable jQuery UI datepicker available at:
 * http://jqueryui.com/demos/datepicker/
 * (original distributed under an MIT license)
 * Note: 
 * jslint browser: true, nomen: true
 */

$.datepicker.setDefaults({
    // Setting dayNamesMin to the 'short' names as the _generateHTML
    // method of the parent jQuery widget hardcodes the use of the
    // 'min' names, usually 'Su', Mo', 'Tu', 'We', 'Th' et cetera.
    dayNamesMin: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"],
    // Slowing down the hide animation so that users get to see their
    // day selection before we get rid of the datepicker.
    duration: "slow"
});