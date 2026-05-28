// ===== FOOD DATA =====
const foodData = [
  { id: 1, name: 'Masala Dosa', meal: 'breakfast', desc: 'Crispy rice crepe with spiced potato filling, served with coconut chutney and sambar.', price: 220, img: 'https://images.unsplash.com/photo-1630383249896-424e482df921?w=400&q=80', avail: true },
  { id: 2, name: 'Pancake Stack', meal: 'breakfast', desc: 'Fluffy buttermilk pancakes with maple syrup, fresh berries, and whipped butter.', price: 350, img: 'https://images.unsplash.com/photo-1567620905732-2d1ec7ab7445?w=400&q=80', avail: true },
  { id: 3, name: 'Avocado Toast', meal: 'breakfast', desc: 'Sourdough toast with smashed avocado, poached egg, cherry tomatoes, and microgreens.', price: 280, img: 'https://images.unsplash.com/photo-1541519227354-08fa5d50c44d?w=400&q=80', avail: true },
  { id: 4, name: 'Fruit Bowl', meal: 'breakfast', desc: 'Fresh seasonal fruits with yogurt, honey, granola, and mint garnish.', price: 190, img: 'https://images.unsplash.com/photo-1490474418585-ba9bad8fd0ea?w=400&q=80', avail: true },
  { id: 5, name: 'Chicken Biryani', meal: 'lunch', desc: 'Fragrant basmati rice layered with spiced chicken, caramelized onions, and saffron.', price: 520, img: 'https://images.unsplash.com/photo-1589302168068-964664d93dc0?w=400&q=80', avail: true },
  { id: 6, name: 'Paneer Butter Masala', meal: 'lunch', desc: 'Rich and creamy tomato-based curry with cottage cheese cubes, butter, and aromatic spices.', price: 380, img: 'https://images.unsplash.com/photo-1631452180519-c014fe946bc7?w=400&q=80', avail: true },
  { id: 7, name: 'Grilled Sandwich', meal: 'lunch', desc: 'Toasted ciabatta with grilled vegetables, mozzarella, pesto, and balsamic glaze.', price: 320, img: 'https://images.unsplash.com/photo-1528735602780-2552fd46c7af?w=400&q=80', avail: true },
  { id: 8, name: 'Caesar Salad', meal: 'lunch', desc: 'Crisp romaine lettuce with parmesan, croutons, grilled chicken, and house-made dressing.', price: 290, img: 'https://images.unsplash.com/photo-1546793665-c74683f339c1?w=400&q=80', avail: true },
  { id: 9, name: 'Grilled Salmon', meal: 'dinner', desc: 'Atlantic salmon fillet with lemon butter sauce, seasonal vegetables, and herbed rice.', price: 890, img: 'https://images.unsplash.com/photo-1467003909585-2f8a72700288?w=400&q=80', avail: true },
  { id: 10, name: 'Steak au Poivre', meal: 'dinner', desc: 'Prime ribeye with peppercorn cream sauce, roasted potatoes, and sauteed asparagus.', price: 1200, img: 'https://images.unsplash.com/photo-1558030006-450675393462?w=400&q=80', avail: true },
  { id: 11, name: 'Pasta Alfredo', meal: 'dinner', desc: 'Fettuccine in creamy parmesan sauce with garlic, mushrooms, and grilled chicken.', price: 480, img: 'https://images.unsplash.com/photo-1645112411341-6c4fd023714a?w=400&q=80', avail: true },
  { id: 12, name: 'Tom Yum Soup', meal: 'dinner', desc: 'Hot and sour Thai soup with shrimp, mushrooms, lemongrass, and galangal.', price: 380, img: 'https://images.unsplash.com/photo-1548943487-a2e4e43b4853?w=400&q=80', avail: true },
  { id: 13, name: 'Chocolate Lava Cake', meal: 'dessert', desc: 'Warm chocolate cake with molten center, vanilla ice cream, and raspberry coulis.', price: 320, img: 'https://images.unsplash.com/photo-1624353365286-3f8d62daad51?w=400&q=80', avail: true },
  { id: 14, name: 'Tiramisu', meal: 'dessert', desc: 'Classic Italian dessert with espresso-soaked ladyfingers, mascarpone, and cocoa dusting.', price: 280, img: 'https://images.unsplash.com/photo-1571877227200-a0d98ea607e9?w=400&q=80', avail: true },
  { id: 15, name: 'Gulab Jamun', meal: 'dessert', desc: 'Deep-fried milk dumplings soaked in rose-scented sugar syrup, served warm.', price: 180, img: 'https://images.unsplash.com/photo-1666190050260-2703a2cc9c55?w=400&q=80', avail: true },
  { id: 16, name: 'Mango Cheesecake', meal: 'dessert', desc: 'Creamy mango cheesecake on a buttery graham crust with mango glaze topping.', price: 350, img: 'https://images.unsplash.com/photo-1533134242443-d4fd215305ad?w=400&q=80', avail: true },
]; let foodNextId = 17;

const orderData = [
  { id: 1001, customer: 'Rahul Sharma', table: 4, items: 'Masala Dosa x2, Cold Coffee', total: 620, status: 'preparing' },
  { id: 1002, customer: 'Priya Patel', table: 7, items: 'Chicken Biryani, Gulab Jamun', total: 700, status: 'completed' },
  { id: 1003, customer: 'Amit Singh', table: 2, items: 'Grilled Sandwich x3', total: 960, status: 'pending' },
  { id: 1004, customer: 'Sneha Reddy', table: 5, items: 'Paneer Butter Masala, Masala Dosa', total: 600, status: 'preparing' },
  { id: 1005, customer: 'Vikram Joshi', table: 1, items: 'Pasta Alfredo, Pancake Stack', total: 830, status: 'completed' },
  { id: 1006, customer: 'Neha Gupta', table: 3, items: 'Margherita Pizza, Chicken Biryani', total: 970, status: 'pending' },
]; let orderNextId = 1007;

const inventoryData = [
  { id: 1, name: 'All-Purpose Flour', category: 'Baking', stock: 45, unit: 'kg', status: 'normal' },
  { id: 2, name: 'Mozzarella Cheese', category: 'Dairy', stock: 12, unit: 'kg', status: 'low' },
  { id: 3, name: 'Tomato Puree', category: 'Sauces', stock: 28, unit: 'liters', status: 'normal' },
  { id: 4, name: 'Chicken Breast', category: 'Meat', stock: 18, unit: 'kg', status: 'normal' },
  { id: 5, name: 'Basmati Rice', category: 'Grains', stock: 35, unit: 'kg', status: 'normal' },
  { id: 6, name: 'Olive Oil', category: 'Oils', stock: 8, unit: 'liters', status: 'low' },
  { id: 7, name: 'Cocoa Powder', category: 'Baking', stock: 3, unit: 'kg', status: 'critical' },
  { id: 8, name: 'Fresh Cream', category: 'Dairy', stock: 5, unit: 'liters', status: 'critical' },
  { id: 9, name: 'Bell Peppers', category: 'Vegetables', stock: 22, unit: 'kg', status: 'normal' },
  { id: 10, name: 'Cheddar Cheese', category: 'Dairy', stock: 15, unit: 'kg', status: 'normal' },
];

const roomPrices = { single: 2499, double: 3999, deluxe: 5999, suite: 9999 };
const roomLabels = { single: 'Single Room', double: 'Double Room', deluxe: 'Deluxe Room', suite: 'Executive Suite' };

let currentFoodTab = 'breakfast';
let editFoodId = null;
let bookingData = null;

// ===== NAVIGATION =====
function navigate(page) {
  document.querySelectorAll('.page-section').forEach(s => s.classList.remove('active'));
  document.querySelectorAll('.nav-item').forEach(n => n.classList.remove('active'));
  const target = document.getElementById(`page-${page}`);
  if (target) target.classList.add('active');
  const navItem = document.querySelector(`.nav-item[data-page="${page}"]`);
  if (navItem) navItem.classList.add('active');
  if (window.innerWidth <= 768) document.querySelector('.sidebar').classList.remove('open');
}

// ===== SIDEBAR TOGGLE & INIT =====
document.addEventListener('DOMContentLoaded', () => {
  const toggle = document.getElementById('sidebarToggle');
  const sidebar = document.querySelector('.sidebar');
  const main = document.querySelector('.main');
  if (toggle) {
    toggle.addEventListener('click', () => {
      if (window.innerWidth <= 768) { sidebar.classList.toggle('open'); }
      else { sidebar.classList.toggle('collapsed'); main.classList.toggle('expanded'); }
    });
  }

  document.querySelectorAll('.nav-item').forEach(item => {
    item.addEventListener('click', () => navigate(item.dataset.page));
  });

  function updateClock() {
    const now = new Date();
    const opts = { weekday: 'short', month: 'short', day: 'numeric', hour: '2-digit', minute: '2-digit' };
    const el = document.getElementById('clock');
    if (el) el.textContent = now.toLocaleDateString('en-US', opts);
  }
  updateClock(); setInterval(updateClock, 10000);

  animateCounters();
  renderFoodGrid();
  renderOrdersTable();
  renderInventoryTable();
  renderDashboardOrders();
  if (typeof Chart !== 'undefined') initCharts();

  // Room card clicks
  document.querySelectorAll('.room-card').forEach(card => {
    card.addEventListener('click', () => {
      document.querySelectorAll('.room-card').forEach(c => c.classList.remove('selected'));
      card.classList.add('selected');
      const select = document.getElementById('bRoomType');
      if (select) {
        for (let opt of select.options) {
          if (opt.value === card.dataset.room) { select.value = card.dataset.room; break; }
        }
      }
    });
  });
});

// ===== ANIMATED COUNTERS =====
function animateCounters() {
  document.querySelectorAll('.stat-value').forEach(el => {
    const target = parseInt(el.dataset.target);
    if (!target) return;
    const duration = 1200, steps = 30;
    const increment = target / steps;
    let current = 0, count = 0;
    const timer = setInterval(() => {
      count++;
      current += increment;
      if (count >= steps) { el.textContent = '₹' + target.toLocaleString(); clearInterval(timer); }
      else { el.textContent = '₹' + Math.round(current).toLocaleString(); }
    }, duration / steps);
  });
}

// ===== FOOD MANAGEMENT =====
function switchFoodTab(cat) {
  currentFoodTab = cat;
  document.querySelectorAll('.food-tab').forEach(t => t.classList.remove('active'));
  document.querySelector(`.food-tab[onclick*="'${cat}'"]`).classList.add('active');
  const title = document.getElementById('foodCategoryTitle');
  if (title) title.textContent = cat.charAt(0).toUpperCase() + cat.slice(1) + ' Items';
  renderFoodGrid();
}

function renderFoodGrid() {
  const grid = document.getElementById('foodGrid');
  if (!grid) return;
  const items = foodData.filter(f => f.meal === currentFoodTab);
  grid.innerHTML = items.map(f => `
    <div class="food-card">
      <img src="${f.img}" alt="${f.name}" loading="lazy">
      <span class="food-badge">${f.avail ? 'Available' : 'Out of Stock'}</span>
      <div class="food-body">
        <h4>${f.name}</h4>
        <p>${f.desc}</p>
        <div class="food-footer">
          <span class="food-price">₹${f.price}</span>
          <span class="food-avail ${f.avail ? 'yes' : 'no'}">${f.avail ? 'In Stock' : 'Sold Out'}</span>
        </div>
        <div class="food-actions">
          <button class="action-btn" onclick="editFoodItem(${f.id})" title="Edit"><i class="fas fa-edit"></i></button>
          <button class="action-btn danger" onclick="deleteFoodItem(${f.id})" title="Delete"><i class="fas fa-trash-alt"></i></button>
        </div>
      </div>
    </div>
  `).join('');
  if (items.length === 0) {
    grid.innerHTML = '<div style="grid-column:1/-1;text-align:center;padding:40px;color:var(--text-dim);font-size:14px;">No items in this category. Click "Add Item" to create one.</div>';
  }
}

function deleteFoodItem(id) {
  if (!confirm('Delete this food item?')) return;
  const idx = foodData.findIndex(i => i.id === id);
  if (idx > -1) { foodData.splice(idx, 1); renderFoodGrid(); }
}

function openMenuModal(item) {
  editFoodId = item ? item.id : null;
  document.getElementById('menuModalLabel').textContent = item ? 'Edit Food Item' : 'Add Food Item';
  document.getElementById('mName').value = item ? item.name : '';
  document.getElementById('mMeal').value = item ? item.meal : 'breakfast';
  document.getElementById('mDesc').value = item ? item.desc : '';
  document.getElementById('mPrice').value = item ? item.price : '';
  document.getElementById('mImage').value = item ? item.img : '';
  document.getElementById('mAvailable').value = item ? (item.avail ? 'yes' : 'no') : 'yes';
  document.getElementById('menuModal').classList.add('open');
}

function closeMenuModal() { document.getElementById('menuModal').classList.remove('open'); editFoodId = null; }

function saveMenuItem() {
  const name = document.getElementById('mName').value.trim();
  const meal = document.getElementById('mMeal').value;
  const desc = document.getElementById('mDesc').value.trim();
  const price = parseInt(document.getElementById('mPrice').value);
  const img = document.getElementById('mImage').value.trim();
  const avail = document.getElementById('mAvailable').value === 'yes';
  if (!name || !desc || !price) { alert('Fill all required fields'); return; }
  if (editFoodId) {
    const item = foodData.find(i => i.id === editFoodId);
    if (item) { item.name = name; item.meal = meal; item.desc = desc; item.price = price; item.img = img || item.img; item.avail = avail; }
  } else {
    foodData.push({ id: foodNextId++, name, meal, desc, price, img: img || 'https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=400&q=80', avail });
  }
  renderFoodGrid(); closeMenuModal();
}

function editFoodItem(id) {
  const item = foodData.find(i => i.id === id);
  if (item) openMenuModal(item);
}

function filterMenu(val) {
  document.querySelectorAll('#foodGrid .food-card').forEach(card => {
    const name = card.querySelector('h4')?.textContent.toLowerCase() || '';
    card.style.display = name.includes(val.toLowerCase()) ? '' : 'none';
  });
}

// ===== ORDERS =====
function renderOrdersTable() {
  const tbody = document.getElementById('ordersTableBody');
  if (!tbody) return;
  tbody.innerHTML = orderData.map(o => `
    <tr>
      <td>#${o.id}</td>
      <td>${o.customer}</td>
      <td>Table ${o.table}</td>
      <td>${o.items}</td>
      <td>₹${o.total}</td>
      <td><span class="status ${o.status}">${o.status.charAt(0).toUpperCase()+o.status.slice(1)}</span></td>
      <td>
        <button class="action-btn" onclick="updateOrderStatus(${o.id},'preparing')" title="Preparing"><i class="fas fa-fire"></i></button>
        <button class="action-btn" onclick="updateOrderStatus(${o.id},'completed')" title="Complete"><i class="fas fa-check"></i></button>
        <button class="action-btn danger" onclick="cancelOrder(${o.id})" title="Cancel"><i class="fas fa-times"></i></button>
      </td>
    </tr>
  `).join('');
}

function renderDashboardOrders() {
  const tbody = document.getElementById('dashboardOrdersBody');
  if (!tbody) return;
  tbody.innerHTML = orderData.slice(0, 5).map(o => `
    <tr>
      <td>#${o.id}</td>
      <td>${o.customer}</td>
      <td><span class="status ${o.status}">${o.status.charAt(0).toUpperCase()+o.status.slice(1)}</span></td>
      <td>₹${o.total}</td>
    </tr>
  `).join('');
}

function updateOrderStatus(id, status) {
  const o = orderData.find(x => x.id === id);
  if (o) { o.status = status; renderOrdersTable(); renderDashboardOrders(); }
}

function cancelOrder(id) {
  if (!confirm('Cancel this order?')) return;
  const idx = orderData.findIndex(x => x.id === id);
  if (idx > -1) { orderData.splice(idx, 1); renderOrdersTable(); renderDashboardOrders(); }
}

function openOrderModal() {
  document.getElementById('orderModal').classList.add('open');
  document.getElementById('orderModalLabel').textContent = 'New Order';
  document.getElementById('oCustomer').value = '';
  document.getElementById('oTable').value = '';
  document.getElementById('oItems').innerHTML = foodData.filter(m => m.avail).map(m =>
    `<option value="${m.name}|${m.price}">${m.name} - ₹${m.price}</option>`
  ).join('');
  document.getElementById('oQty').value = 1;
  document.getElementById('billDetails').style.display = 'none';
}
function closeOrderModal() { document.getElementById('orderModal').classList.remove('open'); }

function calcBill() {
  const sel = document.getElementById('oItems');
  const qty = parseInt(document.getElementById('oQty').value) || 1;
  if (!sel.value) return;
  const [, priceStr] = sel.value.split('|');
  const price = parseInt(priceStr);
  const subtotal = price * qty;
  const tax = Math.round(subtotal * 0.08);
  const total = subtotal + tax;
  document.getElementById('billDetails').style.display = 'block';
  document.getElementById('billSubtotal').textContent = '₹' + subtotal;
  document.getElementById('billTax').textContent = '₹' + tax;
  document.getElementById('billTotal').textContent = '₹' + total;
  document.getElementById('billData').dataset.subtotal = subtotal;
  document.getElementById('billData').dataset.total = total;
}

function placeOrder() {
  const customer = document.getElementById('oCustomer').value.trim();
  const table = document.getElementById('oTable').value.trim();
  const sel = document.getElementById('oItems');
  const qty = parseInt(document.getElementById('oQty').value) || 1;
  if (!customer || !table || !sel.value) { alert('Fill all fields'); return; }
  const total = parseInt(document.getElementById('billData').dataset.total) || 0;
  const [name] = sel.value.split('|');
  orderData.unshift({
    id: orderNextId++, customer, table: parseInt(table),
    items: `${name} x${qty}`, total, status: 'pending'
  });
  renderOrdersTable(); renderDashboardOrders(); closeOrderModal();
}

// ===== INVENTORY =====
function renderInventoryTable() {
  const tbody = document.getElementById('inventoryTableBody');
  if (!tbody) return;
  tbody.innerHTML = inventoryData.map(i => {
    const pct = i.status === 'critical' ? 15 : i.status === 'low' ? 35 : 70;
    const cls = i.status === 'critical' ? 'low' : i.status === 'low' ? 'medium' : 'high';
    const badgeCls = i.status === 'critical' ? 'inactive' : i.status === 'low' ? 'pending' : 'active';
    return `<tr><td>${i.name}</td><td>${i.category}</td><td>${i.stock} ${i.unit}</td><td><div class="stock-bar"><div class="fill ${cls}" style="width:${pct}%"></div></div></td><td><span class="status ${badgeCls}">${i.status.charAt(0).toUpperCase()+i.status.slice(1)}</span></td></tr>`;
  }).join('');
}

// ===== BOOKING (Admin Dashboard) =====
function calculateBooking() {
  const name = document.getElementById('bName').value.trim();
  const email = document.getElementById('bEmail').value.trim();
  const phone = document.getElementById('bPhone').value.trim();
  const checkin = document.getElementById('bCheckin').value;
  const checkout = document.getElementById('bCheckout').value;
  const roomType = document.getElementById('bRoomType').value;
  if (!name || !email || !phone || !checkin || !checkout) { alert('Please fill all booking fields'); return; }
  const d1 = new Date(checkin), d2 = new Date(checkout);
  if (d2 <= d1) { alert('Check-out must be after check-in'); return; }
  const nights = Math.ceil((d2 - d1) / (1000 * 60 * 60 * 24));
  const total = roomPrices[roomType] * nights;
  bookingData = { name, email, phone, checkin, checkout, roomType, nights, total };
  document.getElementById('bookingSummary').style.display = 'block';
  document.getElementById('summaryRoom').textContent = roomLabels[roomType];
  document.getElementById('summaryCheckin').textContent = checkin;
  document.getElementById('summaryCheckout').textContent = checkout;
  document.getElementById('summaryNights').textContent = nights;
  document.getElementById('summaryTotal').textContent = '₹' + total.toLocaleString();
  openPaymentModal(total);
}

function openPaymentModal(amount) {
  document.getElementById('payAmount').textContent = '₹' + amount.toLocaleString();
  document.getElementById('payTotal').textContent = '₹' + amount.toLocaleString();
  document.getElementById('paymentModal').classList.add('open');
  switchPayment('card');
}

function closePaymentModal() { document.getElementById('paymentModal').classList.remove('open'); }

function switchPayment(method) {
  document.querySelectorAll('#paymentModal .pay-tab').forEach(t => t.classList.remove('active'));
  document.querySelectorAll('#paymentModal .pay-section').forEach(s => s.classList.remove('active'));
  document.querySelector(`#paymentModal .pay-tab[onclick*="'${method}'"]`).classList.add('active');
  document.getElementById('pay' + method.charAt(0).toUpperCase() + method.slice(1)).classList.add('active');
}

function processPayment() {
  const method = document.querySelector('#paymentModal .pay-tab.active');
  const methodName = method ? method.textContent.trim() : 'Card';
  if (!bookingData) { alert('No booking data found'); return; }
  document.getElementById('paymentModal').classList.remove('open');
  const confirm = document.getElementById('confirmDetails');
  confirm.innerHTML = `
    <div class="bill-row"><span>Booking ID</span><span>#BK${String(Date.now()).slice(-6)}</span></div>
    <div class="bill-row"><span>Guest</span><span>${bookingData.name}</span></div>
    <div class="bill-row"><span>Room</span><span>${roomLabels[bookingData.roomType]}</span></div>
    <div class="bill-row"><span>Check-in</span><span>${bookingData.checkin}</span></div>
    <div class="bill-row"><span>Check-out</span><span>${bookingData.checkout}</span></div>
    <div class="bill-row"><span>Nights</span><span>${bookingData.nights}</span></div>
    <div class="bill-row"><span>Payment</span><span>${methodName}</span></div>
    <div class="bill-row total"><span>Paid</span><span>₹${bookingData.total.toLocaleString()}</span></div>
  `;
  document.getElementById('confirmModal').classList.add('open');
}

function closeConfirmModal() { document.getElementById('confirmModal').classList.remove('open'); bookingData = null; }
