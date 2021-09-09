function change() {
    var responseID = $('.role').val();
    if(responseID == "Intervenant"){
        $('#hid').removeClass("hidden");
        $('#hid').addClass("show");
    } else{
        $('#hid').removeClass("show");
        $('#hid').addClass("hidden");
        $('#hid1').addClass("hidden1");
    }
    console.log(responseID);
}


