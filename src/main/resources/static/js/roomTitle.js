var sentence = document.querySelector('.sentence');
var h1 = document.querySelector('h1');
var h2 = document.querySelector('h2');

var wordsToArray = function wordsToArray(str) {
    return str.split('').map(function (e) {
        return e === ' ' ? '&nbsp;' : e;
    });
};

function insertSpan(elem, letters, startTime) {
    elem.textContent = '';
    var curr = 0;
    var delay = 20;
    var _iteratorNormalCompletion = true;
    var _didIteratorError = false;
    var _iteratorError = undefined;

    try {
        for (var _iterator = letters[Symbol.iterator](), _step; !(_iteratorNormalCompletion = (_step = _iterator.next()).done); _iteratorNormalCompletion = true) {
            var letter = _step.value;

            var span = document.createElement('span');
            span.innerHTML = letter;
            span.style.animationDelay = ++curr / delay + (startTime || 0) + 's';
            elem.appendChild(span);
        }
    } catch (err) {
        _didIteratorError = true;
        _iteratorError = err;
    } finally {
        try {
            if (!_iteratorNormalCompletion && _iterator.return) {
                _iterator.return();
            }
        } finally {
            if (_didIteratorError) {
                throw _iteratorError;
            }
        }
    }
}

insertSpan(h1, wordsToArray(h1.textContent));
insertSpan(h2, wordsToArray(h2.textContent), .5);


document.addEventListener('click', function (e) {
        var xpos = e.layerX || e.offsetX;
        var ypos = e.layerY || e.offsetY;

        var ax = -(window.innerWidth / 2 - xpos) / 20;
        var ay = (window.innerHeight / 2 - ypos) / 10;

        sentence.style.transform = 'rotateY(0) rotateX(0)';
    }
);