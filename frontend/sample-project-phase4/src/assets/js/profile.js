var currentPage = 0;

function moreContent(id){
	switchMoreContentButtonActivation(true)
	currentPage = currentPage + 1;
    $.ajax('/notanapi/profile/' + id + '/purchases?numPage=' + currentPage,{
		error: function(){
			console.log('Something went wrong')
			switchMoreContentButtonActivation(false)
		},
		success: function(data){
			console.log(data)
			if (data != undefined && data.content != undefined && data.content.length > 0){
				for(let purchase of data.content){
					addPurchase(purchase)
				}
				switchMoreContentButtonActivation(false)
			} else {
				$('#moreContentButton').css("display", "none");
			}	
		}
	})
};

function moreContentAdmin() {
	switchMoreContentButtonActivation(true)
	currentPage = currentPage + 1;
    $.ajax('/notanapi/profile/purchases?numPage=' + currentPage,{
		error: function(){
			console.log('Something went wrong')
			switchMoreContentButtonActivation(false)
		},
		success: function(data){
			console.log(data)
			if (data != undefined && data.content != undefined && data.content.length > 0){
				for(let purchase of data.content){
					addPurchase(purchase)
				}
				switchMoreContentButtonActivation(false)
			} else {
				$('#moreContentButton').css("display", "none");
			}	
		}
	})
};

function addPurchase(purchase){
    $('#userPurchases').append(
        '<p><a href="/purchase/' + purchase.id + '">'+
          'Factura #' + purchase.id +
        '</a></p>')
}

function switchMoreContentButtonActivation(disabled){
	$('#moreContentButton').attr('disabled', disabled);
	if (disabled){
		$('#moreContentSpinner').css("display", "block");
		$('#moreContentText').css("display", "none");
	} else {
		$('#moreContentSpinner').css("display", "none");
		$('#moreContentText').css("display", "block");
	}
}
