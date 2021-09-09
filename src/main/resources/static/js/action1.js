
function change1() {
    var response = $('.role11').val();

    if(response == "Externe"){
        $('#hid1').removeClass("hidden1");
        $('#hid1').addClass("show");
    } else {
        $('#hid1').removeClass("show");
        $('#hid1').addClass("hidden1");
    }
    console.log(response);
};

function change2() {
    var response = $('.role1').val();

    if(response == "Interne"){
        $('#hid1').removeClass("show");
        $('#hid1').addClass("hidden1");

    } else {
        $('#hid1').removeClass("hidden1");
        $('#hid1').addClass("show");
    }
    console.log(response);
};
