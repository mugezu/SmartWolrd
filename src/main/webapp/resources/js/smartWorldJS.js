var page = 1;
var itemId;
function init(Id) {
    itemId = Id;
}
function nextPageReviews() {
    page++;
    getReviews(itemId);

}
function prevPageReviews() {
    page--;
    getReviews(itemId);
}

function getReviews(itemId) {
    $.ajax({
            url: '/reviews?page=' + page + '&id=' + itemId,
            type: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function (data) {
                var mass = '';
                if (data[0] == null) {
                    if (page == 1) {
                        mass += '<h3 > Очень жаль, но комментарии отсутствуют</h3>';
                    }
                    page--;
                    pages(page, false);
                } else {
                    for (i = 0; i < data.length; i++) {
                        var d = new Date(data[i].date);
                        mass += '<div class="comment"><label style="float: left">' + data[i].idBayer.username + '</label>'
                            + '<label style="float: right">' + d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + '</label>'
                            + '<br><h4>' + data[i].comment + '</h4>'
                            + ' <h4>Понравилось: ' + data[i].liked + '</h4>'
                            + ' <h4> Непонравилось: ' + data[i].notLike + ' </h4></div>';
                    }

                    if (data.length < 4) {
                        pages(page, false);
                    } else {
                        pages(page, true);
                    }
                }
                $("#result_text").html(mass);
            }
        }
    );
}

function pages(page, check) {
    var htmlPage;
    if (page > 1 && !check) {
        htmlPage = '<input type="button" value="Предыдущая" onclick="prevPageReviews()">';
    } else if (page == 1 && check) {
        htmlPage = '<input type="button" value="Следующая" onclick="nextPageReviews()">';
    } else if (page > 1 && check) {
        htmlPage = '<input type="button" value="Предыдущая" onclick="prevPageReviews()">' +
            '<input type="button" value="Следующая" onclick="nextPageReviews()">';
    } else {
        htmlPage = '';
    }
    $("#page").html(htmlPage);
}

function sendComment() {
    var json = $('#review').serialize();
    $.ajax({
        url: '/reviews/sendReview',
        data: json,
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        mimeType: 'application/json',
        success: function (data) {
            alert("Отправил");
        }
    });
}

