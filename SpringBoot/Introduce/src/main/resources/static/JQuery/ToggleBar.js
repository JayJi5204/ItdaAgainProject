
$(function () {
  var $aside = $('aside');

  $('#cursor').click(function () {
    $aside.toggleClass('open');

    if ($aside.hasClass('open')) {
      $aside.css({ transform: 'translateX(100%)' });
    } else {
      $aside.css({ transform: 'translateX(0)' });
    }
  });
});
