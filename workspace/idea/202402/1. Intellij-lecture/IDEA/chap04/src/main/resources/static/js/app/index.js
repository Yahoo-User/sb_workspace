var main = {

    init: function () {
        console.group("main::init() invoked.");

        let _this = this;
        console.log('_this:', this);

        $('#btn-save').on('click', function () {
            console.trace('#btn-save::onclick() invoked.');
            _this.save();
        }); // onclick

        $('#btn-update').on('click', function () {
            console.trace('#btn-update::onclick() invoked.');
            _this.update();
        }); // onclick

        $('#btn-delete').on('click', function () {
            console.trace("#btn-delete::onclick() invoked.");
            _this.delete();
        }); // onclick

        console.groupEnd();
    }, // init

    save: function () {
        console.group("main::save() invoked.");

        let data = {
            title   : $('#title').val(),
            author  : $('#author').val(),
            content : $('#content').val()
        }; // data

        console.log('data:', data);
        console.log('JSON:', JSON.stringify(data));

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('Registered.');
            window.location.href = '/';
        }).fail(function (e) {
            alert(JSON.stringify(e));
        });

        console.groupEnd();
    }, // save

    update: function () {
        console.group("main::update() invoked.");

        let data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        console.log('data:', data);
        console.log('JSON:', JSON.stringify(data));

        let id = $('#id').val();
        console.log('id:', id);

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('Updated.');
            window.location.href = "/";
        }).fail(function (e) {
            alert(JSON.stringify(e));
        });

        console.groupEnd();
    }, // update

    delete: function () {
         console.group("main::delete() invoked.");

        let id = $('#id').val();
        console.log('id:', id);

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf8'
        }).done(function () {
            alert('Deleted.');
            window.location.href = "/";
        }).fail(function (e) {
            alert(JSON.stringify(e));
        });

        console.groupEnd();
    } // delete

};  // main


main.init();
