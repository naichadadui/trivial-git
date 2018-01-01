const nums = document.querySelectorAll('.nums span');
const counter = document.querySelector('.counter');
const finalMessage = document.querySelector('.final');

function runAnimation() {
    nums.forEach(function(num, idx){
        var penultimate = nums.length - 1;
    num.addEventListener('animationend', function(e) {
        if(e.animationName === 'goIn' && idx !== penultimate){
        num.classList.remove('in');
        num.classList.add('out');
    } else if (e.animationName === 'goOut' && num.nextElementSibling){
        num.nextElementSibling.classList.add('in');
    } else {
        counter.classList.add('hide');
        finalMessage.classList.add('show');
    }
});
});
}
