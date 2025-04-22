document.getElementById("logoutBtn")?.addEventListener("click", () => {
  Swal.fire({
    title: "Bạn có chắc chắn muốn đăng xuất?",
    icon: "warning",
    showCancelButton: true,
    confirmButtonText: "Đăng xuất",
    cancelButtonText: "Hủy",
  }).then((result) => {
    if (result.isConfirmed) {
      localStorage.removeItem("info");
      localStorage.removeItem("cart");
      window.location.href = "login.html";
    }
  });
});
