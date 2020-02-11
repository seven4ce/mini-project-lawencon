const app = document.getElementById('root');

const logo = document.createElement('img');
logo.src = 'img/logo.png';
logo.onclick = function(){        
  window.location = `img/logo.png`;
};

const container = document.createElement('div');
container.setAttribute('class', 'container');

app.appendChild(logo);
app.appendChild(container);

      const card1 = document.createElement('div');
      card1.setAttribute('class', 'cardMain');

      const card2 = document.createElement('div');
      card2.setAttribute('class', 'cardMain');

      const card3 = document.createElement('div');
      card3.setAttribute('class', 'cardMain');

      const card4 = document.createElement('div');
      card4.setAttribute('class', 'cardMain');

      const card5 = document.createElement('div');
      card5.setAttribute('class', 'cardMain');

      const h1 = document.createElement('h4');
      h1.textContent = `Barang`;

      const h2 = document.createElement('h4');
      h2.textContent = `Harga Barang`;

      const h3 = document.createElement('h4');
      h3.textContent = `Jenis Barang `;

      const h4 = document.createElement('h4');
      h4.textContent = `Supplier`;

      const h5 = document.createElement('h4');
      h5.textContent = `Transaksi`;

      container.appendChild(card1);
      container.appendChild(card2);
      container.appendChild(card3);
      container.appendChild(card4);
      container.appendChild(card5);

      card1.appendChild(h1);
      card1.onclick = function(){        
        window.location = `Barang.html`;
      };


      card2.appendChild(h2);
	  card2.onclick = function(){        
        window.location = `Harga.html`;
      };
	  
      card3.appendChild(h3);
	  card3.onclick = function(){        
        window.location = `JenisBarang.html`;
      };
	  
      card4.appendChild(h4);
	  card4.onclick = function(){        
        window.location = `Supplier.html`;
      };

      card5.appendChild(h5);
      card5.onclick = function(){        
        window.location = `Transaksi.html`;
      };
