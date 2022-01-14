// </snippet_BookServiceClass>
// <snippet_BookServiceClass>
using API_PTUD_R5.Model;
using MongoDB.Bson;
using MongoDB.Driver;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;

namespace API_PTUD_R5.Service
{
    public class SANPHAMService
    {
        private readonly IMongoCollection<SANPHAM> _books;

        // <snippet_BookServiceConstructor>
        public SANPHAMService(IDatabaseSettings settings)
        {
            var client = new MongoClient(settings.ConnectionString);
            var database = client.GetDatabase(settings.DatabaseName);
            _books = database.GetCollection<SANPHAM>(settings.SanPhamCollectionName);
        }
        // </snippet_BookServiceConstructor>

        public List<SANPHAM> Get() =>
            _books.Find(book => true).ToList();

        public SANPHAM Get(long id) =>
            _books.Find<SANPHAM>(book => book.Id == id).FirstOrDefault();

        public List<SANPHAM> filter(long maphanloai) =>
            _books.Find<SANPHAM>(book => book.MaLoai == maphanloai).ToList();

        public List<SANPHAM> shop(long manhacungcap) =>
            _books.Find<SANPHAM>(book => book.MaNCC == manhacungcap).ToList();

        public List<SANPHAM> TimKiem(string tensp)
        {
            var filter = Builders<SANPHAM>.Filter.Regex("TenSP", new BsonRegularExpression(tensp, "i"));
            return _books.Find(filter).ToList();
        }

        public SANPHAM Create(SANPHAM book)
        {
            book.Id = _books.AsQueryable().Count() + 1;
            _books.InsertOne(book);
            return book;
        }

        public void Update(long id, SANPHAM bookIn) =>
            _books.ReplaceOne(book => book.Id == id, bookIn);

        public void Remove(SANPHAM bookIn) =>
            _books.DeleteOne(book => book.Id == bookIn.Id);

        public void Remove(long id) =>
            _books.DeleteOne(book => book.Id == id);
    }
}
// </snippet_BookServiceClass>

