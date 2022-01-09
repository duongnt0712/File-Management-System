$(document).ready(function() {
    var dataTable = $('#filtertable').DataTable({
        'aoColumnDefs':[{
            'bSortable':false,
            'aTargets':['nosort'],
        }],
        columnDefs:[
            {type:'date-dd-mm-yyyy',aTargets:[5]}
        ],
        'paging': false,
        ordering: false, //hidden sorting arrow 
        "aoColumns":[
            null,
            null,
            null,
            null,
            null,
            null,
            null
        ],
        "order":false,
        "bLengthChange":false,
        "dom":'<"top">ct<"top"p><"clear">'
    });
    $("#filterbox").keyup(function(){
        dataTable.search(this.value).draw();
    });
    $('.delete-Button').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#deleteModal #delRef').attr('href', href);
		$('#deleteModal').modal({show:true});
	}); 
} );
$(document).on('click', '.dropdown-menu', function (e) {
    e.stopPropagation();
});
$(".downloadFile").click(function () {
   setTimeout(function () {
        window.location.reload(1);
    }, 1000);
});


