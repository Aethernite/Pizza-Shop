function completeOrder(){
   if (confirm('Are you sure you want to complete this order?')) {
      return true;
   } else {
      return false
   }
}