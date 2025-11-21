const API_BASE = 'http://localhost:8080';

document.addEventListener('DOMContentLoaded', () => {
    loadAllData();
    setupForms();
    setInterval(updateDashboard, 30000); // Update every 30s
});

function loadAllData() {
    loadUsers();
    loadCategories();
    loadBudgets();
    loadTransactions();
}

function setupForms() {
    document.getElementById('createUserForm').addEventListener('submit', createUser);
    document.getElementById('createCategoryForm').addEventListener('submit', createCategory);
    document.getElementById('createBudgetForm').addEventListener('submit', createBudget);
    document.getElementById('createTransactionForm').addEventListener('submit', createTransaction);
}

// TAB SWITCHING
function showTab(tabName) {
    document.querySelectorAll('.tab-content').forEach(tab => tab.classList.remove('active'));
    document.querySelectorAll('.tab').forEach(btn => btn.classList.remove('active'));
    document.getElementById(tabName + '-tab').classList.add('active');
    event.target.closest('.tab').classList.add('active');
    
    if (tabName === 'dashboard') updateDashboard();
}

// DASHBOARD
function updateDashboard() {
    Promise.all([
        fetch(`${API_BASE}/transactions`).then(r => r.json()),
        fetch(`${API_BASE}/categories`).then(r => r.json()),
        fetch(`${API_BASE}/budgets`).then(r => r.json())
    ]).then(([transactions, categories, budgets]) => {
        // Stats
        document.getElementById('dashTotalTransactions').textContent = transactions.length;
        document.getElementById('dashTotalCategories').textContent = categories.length;
        document.getElementById('dashTotalBudgets').textContent = budgets.length;
        
        // Total & Month expenses
        const total = transactions.reduce((sum, t) => sum + t.amount, 0);
        const now = new Date();
        const monthTotal = transactions
            .filter(t => new Date(t.date).getMonth() === now.getMonth())
            .reduce((sum, t) => sum + t.amount, 0);
        
        document.getElementById('totalExpenses').textContent = `${total} CHF`;
        document.getElementById('monthExpenses').textContent = `${monthTotal} CHF`;
        
        // Top categories
        const catSpending = {};
        transactions.forEach(t => {
            const catName = t.category?.name || 'Unbekannt';
            catSpending[catName] = (catSpending[catName] || 0) + t.amount;
        });
        
        const topCats = Object.entries(catSpending)
            .sort((a, b) => b[1] - a[1])
            .slice(0, 5);
        
        document.getElementById('topCategories').innerHTML = topCats.map(([name, amount]) => `
            <div class="cat-item">
                <span class="cat-name">${name}</span>
                <span class="cat-amount">${amount} CHF</span>
            </div>
        `).join('') || '<p class="empty">Keine Daten</p>';
        
        // Recent transactions
        const recent = transactions.slice(-5).reverse();
        document.getElementById('recentTransactions').innerHTML = recent.map(t => `
            <div class="recent-item">
                <div class="recent-info">
                    <span class="recent-desc">${t.description}</span>
                    <span class="recent-cat">${t.category?.name || ''}</span>
                </div>
                <span class="recent-amount">${t.amount} CHF</span>
            </div>
        `).join('') || '<p class="empty">Keine Transaktionen</p>';
    });
}

// USERS
function loadUsers() {
    fetch(`${API_BASE}/users`)
        .then(r => r.json())
        .then(users => {
            updateUsersDisplay(users);
            updateUserSelects(users);
        })
        .catch(e => console.error('Error loading users:', e));
}

function updateUsersDisplay(users) {
    const html = users.map(u => `
        <div class="user-card">
            <div class="user-avatar">👤</div>
            <div class="user-info">
                <h4>${u.name}</h4>
                <p>${u.email}</p>
            </div>
            <button onclick="deleteUser(${u.id})" class="btn-delete">🗑️</button>
        </div>
    `).join('');
    document.getElementById('usersDisplay').innerHTML = html || '<p class="empty">Keine Users</p>';
}

function updateUserSelects(users) {
    ['transactionUserId', 'categoryUserId', 'budgetUserId'].forEach(id => {
        const select = document.getElementById(id);
        select.innerHTML = '<option value="">Wählen...</option>' +
            users.map(u => `<option value="${u.id}">${u.name}</option>`).join('');
    });
}

function createUser(e) {
    e.preventDefault();
    const formData = new FormData(e.target);
    const user = {
        name: formData.get('name'),
        email: formData.get('email'),
        createdAt: new Date().toISOString()
    };
    
    fetch(`${API_BASE}/users`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(user)
    })
    .then(r => r.json())
    .then(() => {
        e.target.reset();
        loadUsers();
        showNotification('✓ User erstellt');
    })
    .catch(e => showNotification('✗ Fehler: ' + e.message, 'error'));
}

function deleteUser(id) {
    if (!confirm('User wirklich löschen?')) return;
    fetch(`${API_BASE}/users/${id}`, {method: 'DELETE'})
        .then(() => {
            loadUsers();
            showNotification('✓ User gelöscht');
        });
}

// CATEGORIES
function loadCategories() {
    fetch(`${API_BASE}/categories`)
        .then(r => r.json())
        .then(cats => {
            updateCategoriesDisplay(cats);
            updateCategorySelects(cats);
        })
        .catch(e => console.error('Error loading categories:', e));
}

function updateCategoriesDisplay(cats) {
    const html = cats.map(c => `
        <div class="category-card">
            <h4>🏷️ ${c.name}</h4>
            <p>${c.description || ''}</p>
            <small>User: ${c.user?.name || 'N/A'}</small>
            <button onclick="deleteCategory(${c.id})" class="btn-delete">🗑️</button>
        </div>
    `).join('');
    document.getElementById('categoriesDisplay').innerHTML = html || '<p class="empty">Keine Kategorien</p>';
}

function updateCategorySelects(cats) {
    ['transactionCategoryId', 'budgetCategoryId'].forEach(id => {
        const select = document.getElementById(id);
        select.innerHTML = '<option value="">Wählen...</option>' +
            cats.map(c => `<option value="${c.id}">${c.name}</option>`).join('');
    });
}

function createCategory(e) {
    e.preventDefault();
    const formData = new FormData(e.target);
    const cat = {
        name: formData.get('name'),
        description: formData.get('description'),
        user: {id: parseInt(formData.get('userId'))}
    };
    
    fetch(`${API_BASE}/categories`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(cat)
    })
    .then(r => r.json())
    .then(() => {
        e.target.reset();
        loadCategories();
        showNotification('✓ Kategorie erstellt');
    })
    .catch(e => showNotification('✗ Fehler: ' + e.message, 'error'));
}

function deleteCategory(id) {
    if (!confirm('Kategorie wirklich löschen?')) return;
    fetch(`${API_BASE}/categories/${id}`, {method: 'DELETE'})
        .then(() => {
            loadCategories();
            showNotification('✓ Kategorie gelöscht');
        });
}

// BUDGETS
function loadBudgets() {
    fetch(`${API_BASE}/budgets`)
        .then(r => r.json())
        .then(updateBudgetsDisplay)
        .catch(e => console.error('Error loading budgets:', e));
}

function updateBudgetsDisplay(budgets) {
    const months = ['Jan', 'Feb', 'Mär', 'Apr', 'Mai', 'Jun', 'Jul', 'Aug', 'Sep', 'Okt', 'Nov', 'Dez'];
    const html = budgets.map(b => `
        <div class="budget-card">
            <div class="budget-header">
                <h4>🎯 ${b.category?.name || 'N/A'}</h4>
                <span class="budget-amount">${b.amount} CHF</span>
            </div>
            <p>${months[b.month-1]} ${b.year}</p>
            <small>User: ${b.user?.name || 'N/A'}</small>
            <button onclick="deleteBudget(${b.id})" class="btn-delete">🗑️</button>
        </div>
    `).join('');
    document.getElementById('budgetsDisplay').innerHTML = html || '<p class="empty">Keine Budgets</p>';
}

function createBudget(e) {
    e.preventDefault();
    const formData = new FormData(e.target);
    const budget = {
        amount: parseInt(formData.get('amount')),
        month: parseInt(formData.get('month')),
        year: parseInt(formData.get('year')),
        user: {id: parseInt(formData.get('userId'))},
        category: {id: parseInt(formData.get('categoryId'))}
    };
    
    fetch(`${API_BASE}/budgets`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(budget)
    })
    .then(r => r.json())
    .then(() => {
        e.target.reset();
        loadBudgets();
        showNotification('✓ Budget erstellt');
    })
    .catch(e => showNotification('✗ Fehler: ' + e.message, 'error'));
}

function deleteBudget(id) {
    if (!confirm('Budget wirklich löschen?')) return;
    fetch(`${API_BASE}/budgets/${id}`, {method: 'DELETE'})
        .then(() => {
            loadBudgets();
            showNotification('✓ Budget gelöscht');
        });
}

// TRANSACTIONS
function loadTransactions() {
    fetch(`${API_BASE}/transactions`)
        .then(r => r.json())
        .then(updateTransactionsDisplay)
        .catch(e => console.error('Error loading transactions:', e));
}

function updateTransactionsDisplay(transactions) {
    const html = transactions.map(t => `
        <tr>
            <td><strong>${t.amount} CHF</strong></td>
            <td>${t.description}</td>
            <td>${new Date(t.date).toLocaleString('de-CH')}</td>
            <td>${t.user?.name || 'N/A'}</td>
            <td><span class="badge">${t.category?.name || 'N/A'}</span></td>
            <td>
                <button onclick="deleteTransaction(${t.id})" class="btn-delete-small">🗑️</button>
            </td>
        </tr>
    `).join('');
    document.getElementById('transactionsDisplay').innerHTML = html || '<tr><td colspan="6" class="empty">Keine Transaktionen</td></tr>';
}

function createTransaction(e) {
    e.preventDefault();
    const formData = new FormData(e.target);
    const transaction = {
        amount: parseInt(formData.get('amount')),
        description: formData.get('description'),
        date: formData.get('date'),
        createdAt: new Date().toISOString(),
        user: {id: parseInt(formData.get('userId'))},
        category: {id: parseInt(formData.get('categoryId'))}
    };
    
    fetch(`${API_BASE}/transactions`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(transaction)
    })
    .then(r => r.json())
    .then(() => {
        e.target.reset();
        loadTransactions();
        updateDashboard();
        showNotification('✓ Transaktion erstellt');
    })
    .catch(e => showNotification('✗ Fehler: ' + e.message, 'error'));
}

function deleteTransaction(id) {
    if (!confirm('Transaktion wirklich löschen?')) return;
    fetch(`${API_BASE}/transactions/${id}`, {method: 'DELETE'})
        .then(() => {
            loadTransactions();
            updateDashboard();
            showNotification('✓ Transaktion gelöscht');
        });
}

// NOTIFICATIONS
function showNotification(message, type = 'success') {
    const notification = document.createElement('div');
    notification.className = `notification ${type}`;
    notification.textContent = message;
    document.body.appendChild(notification);
    
    setTimeout(() => {
        notification.classList.add('show');
    }, 10);
    
    setTimeout(() => {
        notification.classList.remove('show');
        setTimeout(() => notification.remove(), 300);
    }, 3000);
}
