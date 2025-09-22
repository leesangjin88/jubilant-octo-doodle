/**
 * 
 */
function showMessageModal(message) {
    document.getElementById('messageText').textContent = message;
    const modal = new bootstrap.Modal(document.getElementById('messageModal'));
    modal.show();
}