function validateForm()
{
    var a=document.forms["Form"]["email"].value;
    var b=document.forms["Form"]["username"].value;
    var c=document.forms["Form"]["password"].value;
    var d=document.forms["Form"]["address"].value;
    var e=document.forms["Form"]["phone"].value;

    if (a==null || a=="",b==null || b=="",c==null || c=="",d==null || d=="",e==null || e=="")
    {
        alert("Please Fill All Required Fields");
        return false;
    }
    var email = document.getElementById('email').value;
    var emailRGEX = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    var emailResult = emailRGEX.test(email);
    if(emailResult == false){
        alert('Please enter a valid email');
        return false;
    }

    var username = document.getElementById('username').value;
    var usernameRGEX = /^[a-z0-9_-]{6,15}$/;
    var usernameResult = usernameRGEX.test(username);
    if(usernameResult == false){
        alert('Please enter a valid username. 6-15 symbols, no special symbols!');
        return false;
    }


    var phoneNumber = document.getElementById('phone').value;
    var phoneRGEX = /^[(]{0,1}[0-9]{3}[)]{0,1}[-\s\.]{0,1}[0-9]{3}[-\s\.]{0,1}[0-9]{4}$/;
    var phoneResult = phoneRGEX.test(phoneNumber);
    if(phoneResult == false)
    {
        alert('Please enter a valid phone number');
        return false;
    }
    return true;
}

