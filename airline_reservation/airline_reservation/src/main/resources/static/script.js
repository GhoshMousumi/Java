
document.addEventListener('DOMContentLoaded', () => {
    const bookingForm = document.querySelector('form');

    if (bookingForm) {
        bookingForm.addEventListener('submit', (e) => {
            const seatsInput = bookingForm.querySelector('input[name="seats"]');
            if (seatsInput) {
                const seats = parseInt(seatsInput.value, 10);
                if (isNaN(seats) || seats < 1) {
                    e.preventDefault();
                    alert('Please enter a valid number of seats (1 or more).');
                    seatsInput.focus();
                }
            }
        });
    }
});
