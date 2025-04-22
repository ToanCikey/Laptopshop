function updateCartCount() {
  const cart = JSON.parse(localStorage.getItem("cart")) || [];
  const totalItems = cart.length;
  document.getElementById("cart-count").textContent = totalItems;
}
updateCartCount();
