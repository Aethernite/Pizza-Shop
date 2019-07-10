function validateForm()
{
    var a=document.forms["Form"]["username"].value;
    var b=document.forms["Form"]["password"].value;

    if (a==null || a=="",b==null || b=="")
    {
        alert("Please Fill All Required Field");
        return false;
    }
}