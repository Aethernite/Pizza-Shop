var button = document.getElementById("form");

button.onclick = function(){
    if (confirm('Are you sure you want to remove this product?')) {
        return true;
    } else {
        return false
    }
}