// Add message to the conversation.
const addMessageToConversation = function() {

	// Define variable to store message text.
	let messageText = document.querySelector('.conversation__write').value;

	// If message is empty...
	if ( messageText.trim().length == 0 ) {

		// Do nothing.
		return false;

	} else {

		// Add the message text with the bubble to the conversation.
		document.querySelector('.conversation__body').insertAdjacentHTML('beforeend', `<div class="conversation__bubble conversation__bubble--right"><p class="conversation__text">${messageText}</p></div>`);

		// Clear the input.
		document.querySelector('.conversation__write').value = '';

		// Return focus to input.
		document.querySelector('.conversation__write').focus();
	}

};

// Show chat list.


// Minimize chat.
document.querySelectorAll('.conversation__info, .btn--close').forEach(function(e) {

	e.addEventListener('click', function() {

		document.querySelector('.mask').classList.toggle('minimize');

	});
});




// Send message with send button.
document.querySelector('.btn--send').addEventListener('click', addMessageToConversation);

// Send message with enter key.
document.querySelector('.conversation__write').addEventListener('keydown', function(e) {

	if (e.key == 'Enter') {

		addMessageToConversation();

	} else {

		return false;
	}
});