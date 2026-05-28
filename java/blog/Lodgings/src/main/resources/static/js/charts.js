function initCharts() {

  // ===== REVENUE LINE CHART =====
  const revCtx = document.getElementById('revenueChart');
  if (revCtx) {
    new Chart(revCtx, {
      type: 'line',
      data: {
        labels: ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'],
        datasets: [{
          label: 'Revenue 2026',
          data: [18500, 22300, 19800, 26500, 31200, 28900, 34100, 37800, 35400, 40200, 43100, 48500],
          borderColor: '#4f8cff',
          backgroundColor: 'rgba(79,140,255,0.08)',
          fill: true,
          tension: 0.4,
          pointBackgroundColor: '#4f8cff',
          pointBorderColor: '#0a0e27',
          pointBorderWidth: 2,
          pointRadius: 4,
          borderWidth: 2.5
        }, {
          label: 'Revenue 2025',
          data: [14200, 16800, 15200, 19400, 22400, 20700, 25800, 28400, 26100, 30500, 32800, 36200],
          borderColor: 'rgba(255,215,0,0.6)',
          backgroundColor: 'rgba(255,215,0,0.05)',
          fill: true,
          tension: 0.4,
          pointBackgroundColor: 'rgba(255,215,0,0.6)',
          pointBorderColor: '#0a0e27',
          pointBorderWidth: 2,
          pointRadius: 3,
          borderWidth: 2,
          borderDash: [5,5]
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: { legend: { labels: { color: '#8890b0', font: { size: 12 } } } },
        scales: {
          x: { grid: { color: 'rgba(255,255,255,0.03)' }, ticks: { color: '#8890b0', font: { size: 11 } } },
          y: { grid: { color: 'rgba(255,255,255,0.04)' }, ticks: { color: '#8890b0', font: { size: 11 }, callback: v => '₹'+v.toLocaleString() } }
        }
      }
    });
  }

  // ===== ORDER STATUS DOUGHNUT =====
  const orderCtx = document.getElementById('orderChart');
  if (orderCtx) {
    new Chart(orderCtx, {
      type: 'doughnut',
      data: {
        labels: ['Completed', 'Preparing', 'Pending', 'Cancelled'],
        datasets: [{
          data: [45, 28, 18, 9],
          backgroundColor: ['#00c853', '#4f8cff', '#ffab00', '#ff5252'],
          borderWidth: 0
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        cutout: '68%',
        plugins: {
          legend: { position: 'bottom', labels: { color: '#8890b0', font: { size: 11 }, padding: 12 } }
        }
      }
    });
  }

  // ===== MONTHLY BAR CHART (Sales page) =====
  const barCtx = document.getElementById('monthlyChart');
  if (barCtx) {
    new Chart(barCtx, {
      type: 'bar',
      data: {
        labels: ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'],
        datasets: [{
          label: 'Monthly Revenue (₹)',
          data: [18500, 22300, 19800, 26500, 31200, 28900, 34100, 37800, 35400, 40200, 43100, 48500],
          backgroundColor: [
            'rgba(79,140,255,0.7)', 'rgba(79,140,255,0.6)', 'rgba(79,140,255,0.5)',
            'rgba(79,140,255,0.7)', 'rgba(79,140,255,0.8)', 'rgba(79,140,255,0.6)',
            'rgba(79,140,255,0.9)', 'rgba(79,140,255,0.7)', 'rgba(79,140,255,0.6)',
            'rgba(79,140,255,0.8)', 'rgba(79,140,255,0.9)', 'rgba(79,140,255,0.7)'
          ],
          borderColor: '#4f8cff',
          borderWidth: 1,
          borderRadius: 4
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: { legend: { labels: { color: '#8890b0', font: { size: 12 } } } },
        scales: {
          x: { grid: { display: false }, ticks: { color: '#8890b0', font: { size: 11 } } },
          y: { grid: { color: 'rgba(255,255,255,0.04)' }, ticks: { color: '#8890b0', font: { size: 11 }, callback: v => '₹'+v.toLocaleString() } }
        }
      }
    });
  }

  // ===== CATEGORY PIE CHART (Sales page) =====
  const pieCtx = document.getElementById('categoryChart');
  if (pieCtx) {
    new Chart(pieCtx, {
      type: 'pie',
      data: {
        labels: ['Pizza', 'Burgers', 'Biryani', 'Desserts', 'Beverages'],
        datasets: [{
          data: [32, 24, 18, 14, 12],
          backgroundColor: ['#4f8cff', '#ffd700', '#00c853', '#ce93d8', '#ffab00'],
          borderWidth: 0
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
          legend: { position: 'bottom', labels: { color: '#8890b0', font: { size: 11 }, padding: 12 } }
        }
      }
    });
  }

  // ===== SALES LINE CHART (Sales page - daily) =====
  const dailyCtx = document.getElementById('dailyChart');
  if (dailyCtx) {
    new Chart(dailyCtx, {
      type: 'line',
      data: {
        labels: Array.from({length: 30}, (_, i) => `Day ${i+1}`),
        datasets: [{
          label: 'Daily Sales (₹)',
          data: [4200,3800,5100,4600,5300,4900,6200,5800,5500,6100,6700,5900,6400,7100,6800,7200,6900,7500,8100,7800,8300,7900,8500,8200,8800,8400,9100,8700,9400,10200],
          borderColor: '#4f8cff',
          backgroundColor: 'rgba(79,140,255,0.1)',
          fill: true,
          tension: 0.4,
          borderWidth: 2.5,
          pointRadius: 3
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: { legend: { labels: { color: '#8890b0', font: { size: 12 } } } },
        scales: {
          x: { grid: { display: false }, ticks: { color: '#8890b0', font: { size: 10 } } },
          y: { grid: { color: 'rgba(255,255,255,0.04)' }, ticks: { color: '#8890b0', font: { size: 11 }, callback: v => '₹'+v.toLocaleString() } }
        }
      }
    });
  }
}
