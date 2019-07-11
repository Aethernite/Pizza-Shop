function validateForm() {
    var a = document.forms["Form"]["name"].value;
    var b = document.forms["Form"]["imgurl"].value;
    var c = document.forms["Form"]["description"].value;
    var d = document.forms["Form"]["price"].value;

    if (a == null || a == "", b == null || b == "", c == null || c == "", d == null || d == "") {
        alert("Please Fill All Required Fields");
        return false;
    }
    return true;
}